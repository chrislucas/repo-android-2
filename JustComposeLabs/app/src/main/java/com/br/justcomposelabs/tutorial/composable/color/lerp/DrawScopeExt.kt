package com.br.justcomposelabs.tutorial.composable.color.lerp

import androidx.annotation.FloatRange
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.drawOnCanvas(
    startColor: Color,
    endColor: Color,
    @FloatRange startFraction: Float,
    createBrush: DrawScope.(startColor: Color, endColor: Color, fraction: Float) -> Brush,
    onCanvas: DrawScope.(Brush) -> Unit
) {
    onCanvas(createBrush(startColor, endColor, startFraction))
}
