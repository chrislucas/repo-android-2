package com.br.justcomposelabs.tutorial.gptstudies.changegridlayout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

// Grid com duas colunas fixas
@Composable
fun GridFixedLayout(
    items: List<String>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
    ) {
        items(items.size) { index ->
            GridItemCard(items[index])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GridFixedLayoutPreview() {
    val sampleItems = List(10) { "Item $it" }
    JustComposeLabsTheme {
        GridFixedLayout(items = sampleItems)
    }
}
