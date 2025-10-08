package com.br.canvasviews.tutorial.google.usingcomposeinview

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.canvasviews.R
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

/*
    https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/compose-in-views
 */
class ComposeInViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                InteractiveUnitCircle()
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Hello $name!")
    }
}

@Composable
fun InteractiveUnitCircle() {
    val sizeDp = 350.dp
    val sizePx = with(LocalDensity.current) { sizeDp.toPx() }

    var angle by remember { mutableStateOf(0f) } // in radians

    // Declare center and radius outside Canvas for use in calculations
    val center = remember { mutableStateOf(Offset.Zero) }
    val radius = remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Canvas with circle, axes, and interaction
            Box(
                modifier = Modifier
                    .size(sizeDp)
                    .pointerInput(Unit) {
                        detectDragGestures { change, _ ->
                            val touchPoint = change.position
                            val c = center.value
                            if (c != Offset.Zero) {
                                val dx = touchPoint.x - c.x
                                val dy = touchPoint.y - c.y
                                val newAngle = atan2(dy, dx)
                                angle = if (newAngle < 0) newAngle + (2 * Math.PI).toFloat() else newAngle
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val c = Offset(sizePx / 2f, sizePx / 2f)
                    center.value = c
                    val r = sizePx / 2.5f
                    radius.value = r

                    // Draw axes X and Y
                    drawLine(
                        color = Color.Gray,
                        start = Offset(0f, c.y),
                        end = Offset(sizePx, c.y),
                        strokeWidth = 2f
                    )
                    drawLine(
                        color = Color.Gray,
                        start = Offset(c.x, 0f),
                        end = Offset(c.x, sizePx),
                        strokeWidth = 2f
                    )

                    // Labels "X" and "Y"
                    drawContext.canvas.nativeCanvas.apply {
                        val paint = android.graphics.Paint().apply {
                            textSize = 30f
                            color = android.graphics.Color.BLACK
                            textAlign = android.graphics.Paint.Align.CENTER
                        }
                        drawText("X", sizePx - 20f, c.y - 10f, paint)
                        drawText("Y", c.x + 10f, 30f, paint)
                    }

                    // Draw circle (green)
                    drawCircle(
                        color = Color.Green,
                        radius = r,
                        style = Stroke(width = 2f),
                        center = c
                    )

                    // Point on circle based on current angle
                    val point = Offset(
                        x = c.x + r * cos(angle),
                        y = c.y + r * sin(angle)
                    )
                    drawCircle(
                        color = Color.Red,
                        radius = 8f,
                        center = point
                    )

                    // Draw blue line (cosine)
                    val cosineEnd = Offset(c.x + r * cos(angle), c.y)
                    drawLine(
                        color = Color.Blue,
                        start = point,
                        end = cosineEnd,
                        strokeWidth = 2f
                    )

                    // Draw red line (sine)
                    val sineEnd = Offset(c.x, c.y + r * sin(angle))
                    drawLine(
                        color = Color.Red,
                        start = point,
                        end = sineEnd,
                        strokeWidth = 2f
                    )

                    // Optional: draw tangent or other lines
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            // Calculate and display angle in degrees and radius
            val angleDeg = Math.toDegrees(angle.toDouble())
            val radiusVal = radius.value
            val sineVal = sin(angle)
            val cosineVal = cos(angle)
            val tangentVal = tan(angle).takeIf { abs(it) < 10f }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Angle: %.1f°".format(angleDeg))
                Text(text = "Radius: %.2f".format(radiusVal))
                Text(text = "Sine: %.3f".format(sineVal))
                Text(text = "Cosine: %.3f".format(cosineVal))
                Text(text = "Tangent: %.3f".format(tangentVal ?: Double.NaN))
            }
        }
    }
}






