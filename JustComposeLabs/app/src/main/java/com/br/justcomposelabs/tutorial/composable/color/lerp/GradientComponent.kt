package com.br.justcomposelabs.tutorial.composable.color.lerp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun GradientComponent(
    onDraw: DrawScope.(Float) -> Unit,
    createComponent: @Composable (onDraw: DrawScope.() -> Unit) -> Unit
) {
    var fraction by remember { mutableFloatStateOf(.5f) }

    Column(
        modifier = Modifier
            .fillMaxSizePadding()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        createComponent {
            onDraw(fraction)
        }
        GradientComponentControl(fraction) { value -> fraction = value }
    }
}

@Composable
@Preview(showBackground = true)
private fun HorizontalGradientComponentPreview() {
    JustComposeLabsTheme {
        val draw: DrawScope.(Float) -> Unit = { fraction ->
            withBrush(
                Color.Blue,
                Color.Green,
                fraction,
                createBrush = { start, end, f ->
                    val width = size.width
                    val intermediateColor = lerp(
                        start,
                        end,
                        f
                    )

                    Brush.horizontalGradient(
                        colors = listOf(start, intermediateColor),
                        startX = 0f,
                        endX = width * f.coerceAtLeast(0.01f)
                    )
                }
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

        val component: @Composable (DrawScope.() -> Unit) -> Unit = {
            CanvasLayout(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                it
            )
        }

        GradientComponent(onDraw = draw, createComponent = component)
    }
}

@Composable
@Preview(showBackground = true)
private fun VerticalGradientComponentPreview() {
    JustComposeLabsTheme {
        val draw: DrawScope.(Float) -> Unit = { fraction ->
            withBrush(
                Color.Blue,
                Color.Green,
                fraction,
                createBrush = { start, end, f ->
                    val height = size.height
                    val intermediateColor = lerp(
                        start,
                        end,
                        f
                    )

                    Brush.verticalGradient(
                        colors = listOf(start, intermediateColor),
                        startY = 0f,
                        endY = height * f.coerceAtLeast(0.01f)
                    )
                }
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

        val component: @Composable (DrawScope.() -> Unit) -> Unit = {
            CanvasLayout(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                it
            )
        }

        GradientComponent(onDraw = draw, createComponent = component)
    }
}


@Preview(showBackground = true)
@Composable
private fun RadialGradientComponentPreview() {
    JustComposeLabsTheme {
        val onDraw: DrawScope.(Float) -> Unit = { fraction ->
            withBrush(
                Color.Blue,
                Color.Green,
                fraction,
                createBrush = { start, end, f ->
                    val intermediateColor = lerp(
                        start,
                        end,
                        f
                    )

                    Brush.radialGradient(
                        colors = listOf(start, intermediateColor),
                        center = Offset(
                            size.width * f.coerceAtLeast(0.01f),
                            size.height * f.coerceAtLeast(0.01f)
                        ),
                        radius = minOf(size.width, size.height) / 2.0f
                    )
                }
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

        val component: @Composable (DrawScope.() -> Unit) -> Unit = {
            CanvasLayout(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                it
            )
        }

        GradientComponent(onDraw = onDraw, createComponent = component)
    }
}


@Preview(showBackground = true)
@Composable
private fun RadialGradientComponentPairPreview() {
    JustComposeLabsTheme {
        val onDraw: DrawScope.(Float) -> Unit = { fraction ->
            withBrush(fraction,
                createBrush = { f ->
                    Brush.radialGradient(
                        colorStops = arrayOf(
                            0.0f to Color.Red,
                            0.3f to Color.Green,
                            1.0f to Color.Blue,
                        ),
                        center = Offset(
                            size.width * f.coerceAtLeast(0.01f),
                            size.height * f.coerceAtLeast(0.01f)
                        ),
                        radius = minOf(size.width, size.height) / 2.0f
                    )
                }
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

        val component: @Composable (DrawScope.() -> Unit) -> Unit = {
            CanvasLayout(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                it
            )
        }

        GradientComponent(onDraw = onDraw, createComponent = component)
    }
}
