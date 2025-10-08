package com.br.justcomposelabs.tutorial.canvas.coord3dsample

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview

import kotlin.math.cos
import kotlin.math.sin

@Preview(showBackground = true, showSystemUi = false, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun Cartesian3DCompose() {
    var rotationX by remember { mutableFloatStateOf(0f) }
    var rotationY by remember { mutableFloatStateOf(0f) }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    rotationX += dragAmount.y * 0.01f
                    rotationY += dragAmount.x * 0.01f
                }
            }
    ) {
        val size = size.minDimension
        val center = Offset(size / 2f, size / 2f)

        // Define pontos 3D dos eixos
        val axisLength = size * 0.4f
        val points3D = listOf(
            floatArrayOf(axisLength, 0f, 0f), // X
            floatArrayOf(-axisLength, 0f, 0f),
            floatArrayOf(0f, axisLength, 0f), // Y
            floatArrayOf(0f, -axisLength, 0f),
            floatArrayOf(0f, 0f, axisLength), // Z
            floatArrayOf(0f, 0f, -axisLength)
        )

        // Rotação em torno de X e Y
        val rotatedPoints = points3D.map { point ->
            val (x, y, z) = point
            // Rotação X
            val y1 = y * cos(rotationX) - z * sin(rotationX)
            val z1 = y * sin(rotationX) + z * cos(rotationX)
            // Rotação Y
            val x2 = x * cos(rotationY) + z1 * sin(rotationY)
            val z2 = -x * sin(rotationY) + z1 * cos(rotationY)
            floatArrayOf(x2, y1, z2)
        }

        // Projeção e desenho
        for (i in rotatedPoints.indices step 2) {
            val start3D = rotatedPoints[i]
            val end3D = rotatedPoints[i + 1]

            val (x1, y1, z1) = start3D
            val (x2, y2, z2) = end3D

            // Perspectiva simplificada
            val scale = (size * 0.5f) / (z1 + size * 0.5f)
            val start2D = Offset(
                center.x + x1 * scale,
                center.y + y1 * scale
            )

            val scaleEnd = (size * 0.5f) / (z2 + size * 0.5f)
            val end2D = Offset(
                center.x + x2 * scaleEnd,
                center.y + y2 * scaleEnd
            )

            drawLine(
                color = Color.Black,
                start = start2D,
                end = end2D,
                strokeWidth = 4f,
                cap = Stroke.DefaultCap
            )
        }
    }
}
