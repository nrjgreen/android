package nl.nrjhub.companion.android.common.data.websocket.impl.entities

import nl.nrjhub.companion.android.common.data.integration.ServiceData

data class DomainResponse(
    val domain: String,
    val services: Map<String, ServiceData>
)
