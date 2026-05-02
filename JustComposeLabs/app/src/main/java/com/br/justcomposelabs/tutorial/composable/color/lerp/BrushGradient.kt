package com.br.justcomposelabs.tutorial.composable.color.lerp

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.utils.composable.fillMaxSizePadding
import com.br.scaffoldttopbarsample.ui.theme.JustComposeLabsTheme

/*
    https://developer.android.com/develop/ui/compose/graphics/draw/brush

    - Brush é um Compose que descreve como objetos sao desenhados na tela
        - Determina a cor pintada na área de desenho
        - Existem alguns Brushes built-in uteis para desenho
            - LinearGradient, RadialGradient ou SolidColor brush
    - Brush pode ser usado com
 */

@Preview(showBackground = true)
@Composable
fun CircleColorHorizontalGradientLayout() {
    var fraction by remember { mutableFloatStateOf(.5f) }

    val startColor = Color.Red
    val endColor = Color.Blue
    // Interpolação de cores baseada no slider
    val intermediateColor = lerp(
        startColor,
        endColor,
        fraction
    )
    Column(
        modifier = Modifier
            .fillMaxSizePadding()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier.size(200.dp),
                onDraw = {
                    val width = size.width
                    val height = size.height
                    val brush = Brush.horizontalGradient(
                        colors = listOf(startColor, intermediateColor),
                        startX = 0f,
                        endX = width * fraction.coerceAtLeast(0.01f)
                    )
                    drawCircle(
                        brush = brush,
                        center = Offset(width / 2f, height / 2f),
                        radius = minOf(width, height) / 2.0f
                    )
                },
            )
        }

        /**
         * Ajustando gradiente e cor via slider
         */

        GradientComponentControl(fraction) { value -> fraction = value }
    }
}

@Preview(showBackground = true)
@Composable
fun CircleColorVerticalGradientLayout() {
    var fraction by remember { mutableFloatStateOf(.5f) }

    val startColor = Color(0xFFFFFFFF)
    val endColor = Color(0xFF000000)
    // Interpolação de cores baseada no slider
    val intermediateColor = lerp(
        startColor,
        endColor,
        fraction
    )
    Column(
        modifier = Modifier
            .fillMaxSizePadding()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier.size(200.dp),
                onDraw = {
                    val width = size.width
                    val height = size.height
                    val brush = Brush.verticalGradient(
                        colors = listOf(startColor, intermediateColor),
                        startY = 0f,
                        endY = height * fraction.coerceAtLeast(0.01f)
                    )

                    drawCircle(
                        brush = brush,
                        center = Offset(width / 2f, height / 2f),
                        radius = minOf(width, height) / 2.0f,
                        style = Stroke(
                            width = 1.0f
                        )
                    )

                    drawCircle(
                        brush = brush,
                        center = Offset(width / 2f, height / 2f),
                        radius = minOf(width, height) / 2.0f,
                    )
                },
            )
        }

        /**
         * Ajustando gradiente e cor via slider
         */

        GradientComponentControl(fraction) { value -> fraction = value }
    }
}

@Composable
fun CanvasLayout(modifier: Modifier, onDraw: DrawScope.() -> Unit) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.size(200.dp),
            onDraw = onDraw,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CanvasLayoutPreview() {
    JustComposeLabsTheme {
        CanvasLayout(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),

            onDraw = {
                withBrush(
                    Color.Blue,
                    Color.Green,
                    0.5f,
                    createBrush = { startColor, endColor, startFraction ->

                        val width = size.width
                        val intermediateColor = lerp(
                            startColor,
                            endColor,
                            startFraction
                        )

                        Brush.horizontalGradient(
                            colors = listOf(startColor, intermediateColor),
                            startX = 0f,
                            endX = width * startFraction.coerceAtLeast(0.01f)
                        )
                    },
                ) { brush ->
                    drawCircle(
                        brush = brush,
                        center = Offset(size.width / 2f, size.height / 2f),
                        radius = minOf(size.width, size.height) / 2.0f
                    )

                    drawCircle(
                        color = Color.Black,
                        center = Offset(size.width / 2f, size.height / 2f),
                        radius = minOf(size.width, size.height) / 2.0f,
                        style = Stroke(15.0f)
                    )
                }
            }
        )
    }
}
