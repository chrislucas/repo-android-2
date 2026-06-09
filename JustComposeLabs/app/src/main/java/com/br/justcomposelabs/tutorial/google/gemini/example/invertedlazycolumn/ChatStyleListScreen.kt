package com.br.justcomposelabs.tutorial.google.gemini.example.invertedlazycolumn

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/*
    https://share.google/aimode/sfQRTf49vGEvh5GW1
*/

data class Message(val text: String, val userId: Int)

data class ChatUI(val messages: List<Message>) {

}

class ChatViewModel() : ViewModel() {

    private val uiState: MutableStateFlow<ChatUI> = MutableStateFlow(
        ChatUI(emptyList())
    )

    val uiStateFlow: StateFlow<ChatUI> = uiState.asStateFlow()

    fun sendMessage(message: Message) {
        uiState.update { currentState ->
            val updatedMessages = currentState.messages + message
            currentState.copy(messages = updatedMessages)
        }
    }
}

@Composable
fun ChatStyleListScreen(
    modifier: Modifier,
    viewModel: ChatViewModel = viewModel()
) {
}