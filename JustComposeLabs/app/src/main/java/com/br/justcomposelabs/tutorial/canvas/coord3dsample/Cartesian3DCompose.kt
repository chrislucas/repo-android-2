package com.br.justcomposelabs.tutorial.canvas.coord3dsample

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.utils.composable.fillMaxSizePadding
import kotlin.math.cos
import kotlin.math.sin


private const val HALF = 0.5f

@Preview(
    showBackground = true,
    showSystemUi = false,
    uiMode = UI_MODE_NIGHT_NO,
)
@Composable
fun Cartesian3DCompose() {
    var rotationX by remember { mutableFloatStateOf(0f) }
    var rotationY by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = Modifier.fillMaxSizePadding(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier =
                Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            // Access coordinates directly to avoid potential value class access issues in some environments
                            val dx = dragAmount.x
                            val dy = dragAmount.y
                            rotationX += dy * 0.01f
                            rotationY += dx * 0.01f
                        }
                    },
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val minDim = if (canvasWidth < canvasHeight) canvasWidth else canvasHeight
            val centerX = canvasWidth / 2f
            val centerY = canvasHeight / 2f

            // Define pontos 3D dos eixos
            val axisLength = minDim * 0.4f
            val points3D =
                listOf(
                    floatArrayOf(axisLength, 0f, 0f), // X
                    floatArrayOf(-axisLength, 0f, 0f),
                    floatArrayOf(0f, axisLength, 0f), // Y
                    floatArrayOf(0f, -axisLength, 0f),
                    floatArrayOf(0f, 0f, axisLength), // Z
                    floatArrayOf(0f, 0f, -axisLength),
                )

            // Rotação em torno de X e Y
            val rotatedPoints =
                points3D.map { point ->
                    val px = point[0]
                    val py = point[1]
                    val pz = point[2]
                    
                    // Rotação X
                    val ry1 = py * cos(rotationX) - pz * sin(rotationX)
                    val rz1 = py * sin(rotationX) + pz * cos(rotationX)
                    
                    // Rotação Y
                    val rx2 = px * cos(rotationY) + rz1 * sin(rotationY)
                    val rz2 = (-px * sin(rotationY)) + (rz1 * cos(rotationY))
                    
                    floatArrayOf(rx2, ry1, rz2)
                }

            // Projeção e desenho
            for (i in rotatedPoints.indices step 2) {
                val p1 = rotatedPoints[i]
                val p2 = rotatedPoints[i + 1]

                val x1 = p1[0]
                val y1 = p1[1]
                val pz1 = p1[2]

                val x2 = p2[0]
                val y2 = p2[1]
                val pz2 = p2[2]

                // Perspetiva simplificada
                val scale1 = (minDim * HALF) / (pz1 + minDim * HALF)
                val start2D = Offset(centerX + x1 * scale1, centerY + y1 * scale1)

                val scale2 = (minDim * HALF) / (pz2 + minDim * HALF)
                val end2D = Offset(centerX + x2 * scale2, centerY + y2 * scale2)

                drawLine(
                    color = Color.Black,
                    start = start2D,
                    end = end2D,
                    strokeWidth = 4f,
                    cap = StrokeCap.Butt
                )
            }
        }
    }
}
