package nl.nrjhub.companion.android.onboarding.discovery

import nl.nrjhub.companion.android.common.data.HomeAssistantVersion
import java.net.URL

data class HomeAssistantInstance(
    val name: String,
    val url: URL,
    val version: HomeAssistantVersion
)
