package nl.nrjhub.companion.android.conversation.views

data class AssistMessage(
    val message: String,
    val isInput: Boolean,
    val isError: Boolean = false
)
