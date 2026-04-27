package com.br.justcomposelabs.tutorial.google.compose.components.listsandgrids

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

/*
    Reacting to scroll position
    https://developer.android.com/develop/ui/compose/lists#react-to-scroll-position
 */

class MessagePreviewParameterProvider :
    CollectionPreviewParameterProvider<Message>(
        listOf(
            Message(1, "A", "B"),
            Message(2, "A", "B"),
            Message(3, "A", "B"),
            Message(4, "A", "B"),
            Message(4, "A", "B"),
            Message(4, "A", "B"),
            Message(4, "A", "B"),
            Message(4, "A", "B"),
            Message(4, "A", "B"),
            Message(4, "A", "B"),
            Message(4, "A", "B"),
        ),
    )

@Composable
fun MessageList(messages: List<Message>) {
    // Remember our own LazyListState
    val listState = rememberLazyListState()

    // Provide it to LazyColumn
    LazyColumn(state = listState) {
        items(messages.size) {
            Text("${messages[it].text}'")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMessageList() {
    JustComposeLabsTheme {
        MessageList(
            listOf(
                Message(1, "A", "B"),
                Message(2, "A", "B"),
                Message(3, "A", "B"),
                Message(4, "A", "B"),
                Message(4, "A", "B"),
                Message(4, "A", "B"),
                Message(4, "A", "B"),
                Message(4, "A", "B"),
                Message(4, "A", "B"),
                Message(4, "A", "B"),
                Message(4, "A", "B"),
            ),
        )
    }
}
