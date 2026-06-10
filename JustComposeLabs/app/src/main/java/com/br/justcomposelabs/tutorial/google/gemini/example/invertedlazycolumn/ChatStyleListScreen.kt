package com.br.justcomposelabs.tutorial.google.gemini.example.invertedlazycolumn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch


/*
    crie um exemplo com um layout com um botao que permite adicionar items a lista
    https://share.google/aimode/sfQRTf49vGEvh5GW1
*/

data class Message(val text: String, val userId: Int)

data class ChatUI(val messages: List<Message>)

class ChatViewModel : ViewModel() {
    private val uiState: MutableStateFlow<ChatUI> = MutableStateFlow(
        ChatUI(emptyList())
    )

    val chatUiState: StateFlow<ChatUI> = uiState.asStateFlow()

    fun sendMessage(message: Message) {
        uiState.update { currentState ->
            val updatedMessages = currentState.messages + message
            currentState.copy(messages = updatedMessages)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatStyleListScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = viewModel()
) {
    /**
     * @see com.br.justcomposelabs.tutorial.google.compose.state.ClockViewModelFlow
    Coleta o estado de forma ciente do ciclo de vida (Lifecycle-aware).
    Interrompe a coleta automaticamente quando o app entra em segundo plano (backstack/background)
    e retoma quando volta para o primeiro plano, economizando recursos de CPU e bateria.
     */
    val chatUIState by viewModel.chatUiState.collectAsStateWithLifecycle()
    ChatStyleListContent(
        modifier = modifier,
        messages = chatUIState.messages,
        sendMessage = viewModel::sendMessage
    )
}

@Preview(showBackground = true)
@Composable
fun ChatStyleListContent(
    modifier: Modifier = Modifier,
    messages: List<Message> = emptyList(),
    sendMessage: (Message) -> Unit = {}
) {
    val listState = rememberLazyListState()
    var textInput by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
            .imePadding()
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxWidth(),
                reverseLayout = true,
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(messages) { message ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (message.userId % 2 == 0) {
                                androidx.compose.material3.MaterialTheme.colorScheme.primary
                            } else {
                                androidx.compose.material3.MaterialTheme.colorScheme.secondary
                            }
                        ),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = message.text,
                            modifier = Modifier.padding(16.dp),
                            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = textInput,
                onValueChange = {
                    textInput = it
                },
                label = { Text("Message") },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type a message") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            /*
                No projeto URLShortener alinhei o botão com a caixa de texto.
             */
            Button(
                onClick = {
                    if (textInput.isNotBlank()){
                        sendMessage(Message(textInput, userId = 1))
                        textInput = ""
                        coroutineScope.launch {
                            listState.animateScrollToItem(0)
                        }
                    }
                },
                shape = RectangleShape
            ) {
                Text("Send")
            }
        }
    }


}

@Preview(showBackground = true, name = "Chat Style List Screen Preview")
@Composable
fun ChatStyleListScreenPreview() {
    val dummyMessages = listOf(
        Message("Olá, como posso ajudar?", 0),
        Message("Gostaria de saber mais sobre Jetpack Compose!", 1),
        Message("Com certeza! O Compose é o kit de ferramentas moderno do Android para criar UIs nativas.", 2),
        Message("Ele simplifica e acelera o desenvolvimento da UI.", 3)
    )

    androidx.compose.material3.MaterialTheme {
        ChatStyleListContent(
            modifier = Modifier,
            messages = dummyMessages
        )
    }
}
