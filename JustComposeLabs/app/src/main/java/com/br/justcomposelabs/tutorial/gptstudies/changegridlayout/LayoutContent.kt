package com.br.justcomposelabs.tutorial.gptstudies.changegridlayout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

// Componente que decide qual layout exibir
@Composable
fun LayoutContent(
    items: List<String>,
    layoutType: LayoutType,
    modifier: Modifier = Modifier,
) {
    when (layoutType) {
        LayoutType.LIST -> ListLayout(items, modifier)
        LayoutType.GRID_FIXED -> GridFixedLayout(items, modifier)
        LayoutType.GRID_FLEXIBLE -> GridFlexibleLayout(items, modifier)
    }
}

@Preview(showBackground = true, name = "List Layout")
@Composable
fun LayoutContentListPreview() {
    val items = List(10) { "Item $it" }
    JustComposeLabsTheme {
        LayoutContent(
            items = items,
            layoutType = LayoutType.LIST
        )
    }
}

@Preview(showBackground = true, name = "Grid Fixed Layout")
@Composable
fun LayoutContentGridFixedPreview() {
    val items = List(10) { "Item $it" }
    JustComposeLabsTheme {
        LayoutContent(
            items = items,
            layoutType = LayoutType.GRID_FIXED
        )
    }
}

@Preview(showBackground = true, name = "Grid Flexible Layout")
@Composable
fun LayoutContentGridFlexiblePreview() {
    val items = List(10) { "Item $it" }
    JustComposeLabsTheme {
        LayoutContent(
            items = items,
            layoutType = LayoutType.GRID_FLEXIBLE
        )
    }
}
