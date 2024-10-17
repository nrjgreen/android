package nl.nrjhub.companion.android.settings

interface SettingsView {
    fun onAddServerResult(success: Boolean, serverId: Int?)
}
