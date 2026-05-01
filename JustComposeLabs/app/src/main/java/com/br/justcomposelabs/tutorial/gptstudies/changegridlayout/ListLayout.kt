package com.br.justcomposelabs.tutorial.gptstudies.changegridlayout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

// Lista simples usando Material3
@Composable
fun ListLayout(
    items: List<String>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier, contentPadding = PaddingValues(8.dp)) {
        items(items.size) { index ->
            ListItemCard(items[index])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListLayoutPreview() {
    val sampleItems = listOf("Apple", "Banana", "Cherry", "Date", "Elderberry")
    JustComposeLabsTheme {
        ListLayout(items = sampleItems)
    }
}
