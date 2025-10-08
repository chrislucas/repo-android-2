package com.br.justcomposelabs.tutorial.google.canvas.graphics.drawtext

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
    https://canopas.com/exploring-text-on-canvas-using-drawtext-api-in-jetpack-compose-402e1285935c
 */
@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ExampleTextAnnotatedString() {

    val textMeasure = rememberTextMeasurer()

    val rainbowColors = listOf(
        Color.Red,
        Color(0xFFFFA500), // Orange
        Color.Yellow,
        Color.Green,
        Color.Blue,
        Color(0xFF4B0082), // Indigo
        Color(0xFF8F00FF)  // Violet
    )

    val text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontSize = 22.sp,
                fontStyle = FontStyle.Italic
            )
        ) {
            append("Hello,")
        }
        withStyle(
            style = SpanStyle(
                brush = Brush.horizontalGradient(colors = rainbowColors),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("\nText on Canvas️")
        }
    }
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        onDraw = {
            drawRect(color = Color.Black)
            drawText(
                textMeasurer = textMeasure,
                text = text,
                topLeft = Offset(10.dp.toPx(), 10.dp.toPx())
            )
        }
    )
}