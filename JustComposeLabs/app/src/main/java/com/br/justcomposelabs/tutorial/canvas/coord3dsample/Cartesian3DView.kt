package com.br.justcomposelabs.tutorial.canvas.coord3dsample

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin
import kotlinx.coroutines.launch

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun CartesianPlan3dAxis() {

    val scope = rememberCoroutineScope()

    // Animatables para rotação suave
    val rotX = remember { Animatable(0f) }
    val rotY = remember { Animatable(0f) }


    val textMeasurer = rememberTextMeasurer()
    val textStyle = TextStyle(fontSize = 23.sp)

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    scope.launch {
                        rotY.animateTo(rotY.value + dragAmount.x * 0.01f)
                        rotX.animateTo(rotX.value + dragAmount.y * 0.01f)
                    }
                }
            }
    ) {
        val w = size.width
        val h = size.height
        val cx = w / 2
        val cy = h / 2
        val axisLen = 300f

        // Pontos finais dos eixos
        val axes = listOf(
            floatArrayOf(axisLen, 0f, 0f),  // X positiva
            floatArrayOf(-axisLen, 0f, 0f), // X negativa
            floatArrayOf(0f, axisLen, 0f),  // Y positiva
            floatArrayOf(0f, -axisLen, 0f), // Y negativa
            floatArrayOf(0f, 0f, axisLen),  // Z positiva
            floatArrayOf(0f, 0f, -axisLen)  // Z negativa
        )
        val colors = listOf(Color.Red, Color.Red, Color.Green, Color.Green, Color.Blue, Color.Blue)

        // Função de rotação no espaço 3D
        fun rotatePoint3D(p: FloatArray, rx: Float, ry: Float): FloatArray {
            val (x, y, z) = p
            val y1 = y * cos(rx) - z * sin(rx)
            val z1 = y * sin(rx) + z * cos(rx)
            val x2 = x * cos(ry) + z1 * sin(ry)
            val z2 = -x * sin(ry) + z1 * cos(ry)
            return floatArrayOf(x2, y1, z2)
        }

        // Projeção perspectiva
        fun project(p: FloatArray): Offset {
            val (x, y, z) = p
            val dist = 500f
            val scale = dist / (dist + z)
            return Offset(cx + x * scale, cy + y * scale)
        }

        // Dentro do seu DrawScope (no Canvas)


        for (i in axes.indices step 2) {
            val startXYZ = axes[i]
            val endXYZ = axes[i + 1]
            val color = colors[i]

            val startRot = rotatePoint3D(startXYZ, rotX.value, rotY.value)
            val endRot = rotatePoint3D(endXYZ, rotX.value, rotY.value)

            val start2D = project(startRot)
            val end2D = project(endRot)

            // Linha do eixo
            drawLine(color = color, start = start2D, end = end2D, strokeWidth = 4f)

            // Preparar texto
            val labelOffset = 40f
            val labelText = when (i) {
                0 -> "X"
                2 -> "Y"
                4 -> "Z"
                else -> ""
            }

            val layoutResult = textMeasurer.measure(AnnotatedString(labelText), style = textStyle)
            val brush = SolidColor(color)

            // Desenha as letras nas extremidades, na mesma cor do eixo
            when (i) {
                0 -> { // eixo X
                    drawText(
                        textLayoutResult = layoutResult,
                        brush = brush,
                        topLeft = Offset(start2D.x - labelOffset, start2D.y)
                    )
                    drawText(
                        textLayoutResult = layoutResult,
                        brush = brush,
                        topLeft = Offset(end2D.x + labelOffset, end2D.y)
                    )
                }

                2 -> { // eixo Y
                    drawText(
                        textLayoutResult = layoutResult,
                        brush = brush,
                        topLeft = Offset(start2D.x, start2D.y - labelOffset)
                    )
                    drawText(
                        textLayoutResult = layoutResult,
                        brush = brush,
                        topLeft = Offset(end2D.x, end2D.y + labelOffset)
                    )
                }

                4 -> { // eixo Z
                    drawText(
                        textLayoutResult = layoutResult,
                        brush = brush,
                        topLeft = Offset(start2D.x, start2D.y + labelOffset)
                    )
                    drawText(
                        textLayoutResult = layoutResult,
                        brush = brush,
                        topLeft = Offset(end2D.x, end2D.y - labelOffset)
                    )
                }
            }
        }
    }
}