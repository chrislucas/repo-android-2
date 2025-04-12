package com.br.funwithjetpackcompose.tutorials.canvas.graphics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun Graphics(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val canvasQuadrantSize = size / 2F
        drawRect(
            color = Color.Companion.Magenta,
            size = canvasQuadrantSize
        )
    }
}