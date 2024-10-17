package nl.nrjhub.companion.android.onboarding.integration

import android.content.Context
import android.util.Log
import androidx.wear.tiles.TileService
import dagger.hilt.android.qualifiers.ActivityContext
import nl.nrjhub.companion.android.BuildConfig
import nl.nrjhub.companion.android.common.data.integration.DeviceRegistration
import nl.nrjhub.companion.android.common.data.servers.ServerManager
import nl.nrjhub.companion.android.onboarding.getMessagingToken
import nl.nrjhub.companion.android.tiles.CameraTile
import nl.nrjhub.companion.android.tiles.ConversationTile
import nl.nrjhub.companion.android.tiles.ShortcutsTile
import nl.nrjhub.companion.android.tiles.TemplateTile
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MobileAppIntegrationPresenterImpl @Inject constructor(
    @ActivityContext context: Context,
    private val serverManager: ServerManager
) : MobileAppIntegrationPresenter {

    companion object {
        internal const val TAG = "IntegrationPresenter"
    }

    private val view = context as MobileAppIntegrationView
    private val mainScope: CoroutineScope = CoroutineScope(Dispatchers.Main + Job())

    private suspend fun createRegistration(deviceName: String): DeviceRegistration {
        return DeviceRegistration(
            "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
            deviceName,
            getMessagingToken(),
            false
        )
    }

    override fun onRegistrationAttempt(serverId: Int, deviceName: String) {
        view.showLoading()
        mainScope.launch {
            val deviceRegistration = createRegistration(deviceName)
            try {
                serverManager.integrationRepository(serverId).registerDevice(deviceRegistration)
                serverManager.convertTemporaryServer(serverId)
            } catch (e: Exception) {
                Log.e(TAG, "Unable to register with Home Assistant", e)
                view.showError()
                return@launch
            }
            updateTiles()
            view.deviceRegistered()
        }
    }

    private fun updateTiles() = mainScope.launch {
        try {
            val context = view as Context
            val updater = TileService.getUpdater(context)
            updater.requestUpdate(CameraTile::class.java)
            updater.requestUpdate(ConversationTile::class.java)
            updater.requestUpdate(ShortcutsTile::class.java)
            updater.requestUpdate(TemplateTile::class.java)
        } catch (e: Exception) {
            Log.w(TAG, "Unable to request tiles update")
        }
    }

    override fun onFinish() {
        mainScope.cancel()
    }
}
