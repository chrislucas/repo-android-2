package com.br.justcomposelabs.tutorial.google.compose.remembersaveable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.tutorial.google.compose.state.wheretohoiststate.Message

/*
    https://developer.android.com/develop/ui/compose/state-saving#ui-logic

    - Se estamos usando a ideia de state hoisting em nossa UI, seja usando composable functions
    ou classes que armazenam estado (ViewModel por exemplo)
 */

@Preview(showBackground = true)
@Composable
fun ChatBubble(
    message: Message =
        Message(
            id = "1",
            content = "Hello World",
            timestamp = "${System.currentTimeMillis()}ms",
        ),
) {
    /**
     * @see com.br.justcomposelabs.tutorial.google.compose.state.wheretohoiststate.ExpandableTextBox
     *
     *
     */
}
