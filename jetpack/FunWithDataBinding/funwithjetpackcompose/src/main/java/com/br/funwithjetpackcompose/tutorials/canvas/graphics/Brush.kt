package com.br.funwithjetpackcompose.tutorials.canvas.graphics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue))

@Preview(showSystemUi = true, showBackground = true,)
@Composable
fun CanvasCircleBrush() = Canvas(modifier = Modifier.size(200.dp)) {
    drawCircle(brush)
}