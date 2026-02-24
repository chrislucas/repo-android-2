package com.br.justcomposelabs.tutorial.google.compose.state.wheretohoiststate

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview


/**
 * TODO: Estudar esse assunto
 * https://developer.android.com/develop/ui/compose/state-hoisting#classes-as-state-owner
- UI Logic
- Quando a logica de UI precisa ler ou rescrever um estado, devmos
restringir esse estado para a UI.
- Podemos fazer isso elevando o estado para um "nível correto" em uma
composable function

- Podemos fazer isso em uma classe especifica para manter estados
- plain state holder class (https://developer.android.com/topic/architecture/ui-layer/stateholders#ui-logic)

Composables as state owner
- https://developer.android.com/develop/ui/compose/state-hoisting#composables-as-state-owner
- Se estamos lidando com logica de estado simples, a documentacao nos diz que
nao ha problemas. Podemos deixar o controle do estado internamente para composable ou "iça-lo / hoist"
se for requisido

No state hoisting needed
- https://developer.android.com/develop/ui/compose/state-hoisting#no-state-hoisting
- Hoisting state nem sempre é requirido. O estado pode ser mantido internamente
nos casos que nenhuma outra composable necessite controla-lo.

 */

/*
    Snippet
    https://github.com/android/snippets/blob/ed5bd1be020d430170fb3f14a1636696195f5733/compose/snippets/src/main/java/com/example/compose/snippets/state/StateHoistingSnippets.kt#L65-L81
 */
data class Message(
    var id: String = "",
    var content: String = "",
    var timestamp: String = ""
)


@Preview(showBackground = true)
@Composable
fun ExpandableTextBox(
    message: Message = Message(
        id = "1",
        content = "Hello World",
        timestamp = "${System.currentTimeMillis()}ms"
    )
) {

    /*

     */
    var showDetails by rememberSaveable { mutableStateOf(false) }

    Text(
        text = AnnotatedString(message.content),
        modifier = Modifier.clickable {
            showDetails = !showDetails
        }
    )

    if (showDetails) {
        Text(message.timestamp)
    }
}