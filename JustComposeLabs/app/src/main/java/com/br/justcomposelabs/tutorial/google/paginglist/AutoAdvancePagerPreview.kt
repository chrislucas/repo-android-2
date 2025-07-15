package com.br.justcomposelabs.tutorial.google.paginglist

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun AutoAdvancePagerPreview() {
    val pageItems: List<Color> = listOf(
        Color.Yellow,
        Color.Gray,
        Color.Green,
        Color.White
    )
    AutoAdvancePager(pageItems = pageItems)
}