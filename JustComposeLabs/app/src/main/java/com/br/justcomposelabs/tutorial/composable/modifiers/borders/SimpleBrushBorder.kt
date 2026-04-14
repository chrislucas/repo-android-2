package com.br.justcomposelabs.tutorial.composable.modifiers.borders

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun SimpleBrushBorder() {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(Color.Blue, Color.Green, Color.Black, Color.Red),
        startX = .0f,
        endX = 500.0f,
        tileMode = TileMode.Repeated
    )

    Text(
        text = "Content",
        modifier = Modifier.border(
            width = 2.dp,
            brush = gradientBrush,
            shape = CircleShape
        ).padding(10.dp)
    )
}
