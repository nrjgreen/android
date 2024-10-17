package nl.nrjhub.companion.android.sensors

import nl.nrjhub.companion.android.BuildConfig
import nl.nrjhub.companion.android.common.sensors.AppSensorManagerBase

class AppSensorManager : AppSensorManagerBase() {

    override fun getCurrentVersion(): String = BuildConfig.VERSION_NAME
}
