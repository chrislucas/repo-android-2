package com.br.justcomposelabs.tutorial.composable.color.interpolation

import androidx.annotation.FloatRange
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.withBrush(
    startColor: Color,
    endColor: Color,
    @FloatRange startFraction: Float,
    createBrush: DrawScope.(startColor: Color, endColor: Color, fraction: Float) -> Brush,
    drawOnCanvas: DrawScope.(Brush) -> Unit
) {
    drawOnCanvas(createBrush(startColor, endColor, startFraction))
}

fun DrawScope.withBrush(
    @FloatRange startFraction: Float,
    createBrush: DrawScope.(fraction: Float) -> Brush,
    drawOnCanvas: DrawScope.(Brush) -> Unit
) {
    drawOnCanvas(createBrush(startFraction))
}
