package com.br.canvasviews.tutorial.google.usingcomposeinview

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

/**
 * Extensões para calcular funções trigonométricas usando graus.
 */

fun asinDegrees(radians: Double): Double = Math.toDegrees(asin(radians))

fun acosDegrees(radians: Double): Double = Math.toDegrees(acos(radians))

fun aTanDegrees(radians: Double): Double = Math.toDegrees(atan(radians))

/*
    https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/compose-in-views
 */

// Constantes para InteractiveUnitCircle
private val CIRCLE_CONTAINER_SIZE_DP = 380.dp
private const val RADIUS_DIVISOR = 2.5f
private const val LABEL_TEXT_SIZE = 50f
private const val AXIS_STROKE_WIDTH = 2f
private const val LABEL_X_OFFSET = 20f
private const val LABEL_Y_OFFSET = 10f
private const val AXIS_LABEL_TOP_PADDING = 20f
private const val UNIT_CIRCLE_STROKE_WIDTH = 4f
private const val INDICATOR_POINT_RADIUS = 8f
private const val GRAPHIC_LINE_STROKE_WIDTH = 2f
private const val TANGENT_EPSILON = 0.001f
private val INFO_SPACER_HEIGHT = 16.dp
private const val TANGENT_DISPLAY_LIMIT = 20f

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
fun InteractiveUnitCircle(modifier: Modifier = Modifier) {
    val sizeDp = CIRCLE_CONTAINER_SIZE_DP
    val density = LocalDensity.current
    val sizePx = with(density) { sizeDp.toPx() }

    // Ângulo atual em radianos
    var radians by remember { mutableFloatStateOf(0.0f) }

    // O centro do círculo (cx, cy) e o raio (r)
    // O círculo é desenhado no centro da área disponível
    val circleCenter = Offset(sizePx / 2f, sizePx / 2f)
    val radius = sizePx / RADIUS_DIVISOR

    // Configuração do Paint para os labels dos eixos
    // Reutilizado para evitar alocações dentro do DrawScope
    val labelAxisPaint =
        remember {
            Paint().apply {
                textSize = LABEL_TEXT_SIZE
                color = android.graphics.Color.BLACK
                textAlign = Paint.Align.CENTER
            }
        }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier =
                    Modifier
                        .size(sizeDp)
                        .border(1.dp, color = Color.Black)
                        .pointerInput(Unit) {
                            detectDragGestures { change, _ ->
                                val touchPoint = change.position
                            /*
                                Calcula o ângulo baseado na posição do toque relativa ao centro
                                atan2(dy, dx) retorna o arco tangente de y/x em radianos (-PI a PI)
                             */

                                val dx = touchPoint.x - circleCenter.x
                                val dy = touchPoint.y - circleCenter.y
                                // Invertemos o dy para que o ângulo cresça no sentido anti-horário
                                // Já que no Canvas do Android o Y cresce para baixo.
                                val newAngle = atan2(-dy, dx)

                                // Normaliza o ângulo para o intervalo [0, 2*PI]
                                radians =
                                    if (newAngle < 0) {
                                        newAngle + (2 * Math.PI).toFloat()
                                    } else {
                                        newAngle
                                    }
                            }
                        },
                contentAlignment = Alignment.Center,
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    // 1. Desenhar Eixos X e Y (Tracejados)
                    val dashPathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

                    drawLine(
                        color = Color.Gray,
                        start = Offset(0f, circleCenter.y),
                        end = Offset(sizePx, circleCenter.y),
                        strokeWidth = AXIS_STROKE_WIDTH,
                        pathEffect = dashPathEffect,
                    )

                    drawLine(
                        color = Color.Gray,
                        start = Offset(circleCenter.x, 0f),
                        end = Offset(circleCenter.x, sizePx),
                        strokeWidth = AXIS_STROKE_WIDTH,
                        pathEffect = dashPathEffect,
                    )

                    // 2. Desenhar Labels "X" e "Y" usando nativeCanvas
                    drawContext.canvas.nativeCanvas.drawText(
                        "X",
                        sizePx - LABEL_X_OFFSET,
                        circleCenter.y - LABEL_Y_OFFSET,
                        labelAxisPaint,
                    )

                    drawContext.canvas.nativeCanvas.drawText(
                        "Y",
                        circleCenter.x + LABEL_Y_OFFSET,
                        AXIS_LABEL_TOP_PADDING,
                        labelAxisPaint,
                    )

                    // 3. Desenhar o Círculo Unitário (em verde)
                    drawCircle(
                        color = Color.Green,
                        radius = radius,
                        style = Stroke(width = UNIT_CIRCLE_STROKE_WIDTH),
                        center = circleCenter,
                    )

                    // 4. Calcular o ponto atual na circunferência
                    // x = cx + r * cos(θ)
                    // y = cy - r * sin(θ)  <-- Subtraímos para sentido anti-horário no Canvas (Y para baixo)
                    val pointOnCircle =
                        Offset(
                            x = circleCenter.x + radius * cos(radians),
                            y = circleCenter.y - radius * sin(radians),
                        )

                    // 5. Desenhar o ponto (indicador)
                    drawCircle(
                        color = Color.Red,
                        radius = INDICATOR_POINT_RADIUS,
                        center = pointOnCircle,
                    )

                    // 6. Desenhar Cosseno (Linha Azul)
                    // O cosseno representa a projeção no eixo X
                    val cosineEnd = Offset(pointOnCircle.x, circleCenter.y)
                    drawLine(
                        color = Color.Blue,
                        start = pointOnCircle,
                        end = cosineEnd,
                        strokeWidth = GRAPHIC_LINE_STROKE_WIDTH,
                        pathEffect = dashPathEffect,
                    )

                    drawLine(
                        color = Color.Blue,
                        start = Offset(circleCenter.x, circleCenter.y),
                        end = cosineEnd,
                        strokeWidth = GRAPHIC_LINE_STROKE_WIDTH,
                    )

                    // 7. Desenhar Seno (Linha Vermelha)
                    // O seno representa a projeção no eixo Y
                    val sineEnd = Offset(circleCenter.x, pointOnCircle.y)
                    drawLine(
                        color = Color.Red,
                        start = pointOnCircle,
                        end = sineEnd,
                        strokeWidth = GRAPHIC_LINE_STROKE_WIDTH,
                        pathEffect = dashPathEffect,
                    )

                    drawLine(
                        color = Color.Red,
                        start = Offset(circleCenter.x, circleCenter.y),
                        end = sineEnd,
                        strokeWidth = GRAPHIC_LINE_STROKE_WIDTH,
                    )

                    // 8. Desenhar Reta Tangente (Linha Magenta)
                    // A tangente intersecta o eixo X no ponto (cx + r/cos(θ), cy)
                    // Evitamos divisão por zero próximo a 90° e 270°
                    val cosA = cos(radians)
                    if (abs(cosA) > TANGENT_EPSILON) {
                        val tangentEndX = circleCenter.x + radius / cosA
                        val tangentEnd = Offset(tangentEndX, circleCenter.y)
                        drawLine(
                            color = Color.Magenta,
                            start = pointOnCircle,
                            end = tangentEnd,
                            strokeWidth = GRAPHIC_LINE_STROKE_WIDTH,
                            pathEffect = dashPathEffect,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(INFO_SPACER_HEIGHT))

            // Informações Numéricas
            val angleDeg = Math.toDegrees(radians.toDouble())

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Graus: %.1f°".format(angleDeg))
                Text(text = "Radianos: %.1f°".format(radians))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.weight(.5f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text("Trigonometria")
                        val sineVal = sin(radians)
                        val cosineVal = cos(radians)
                        val tangentVal =
                            tan(radians).takeIf {
                                // Limita exibição de valores extremos
                                abs(it) < TANGENT_DISPLAY_LIMIT
                            }
                        Text(text = "Seno: %.3f".format(sineVal), color = Color.Red)
                        Text(text = "Cosseno: %.3f".format(cosineVal), color = Color.Blue)
                        Text(text = "Tangente: %.3f".format(tangentVal ?: Double.NaN), color = Color.Magenta)
                    }

                    Column(
                        modifier = Modifier.weight(.5f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text("Inversas (Graus)")
                        val aSineDegreesVal = asinDegrees(radians.toDouble())
                        val aCosineDegreesVal = acosDegrees(radians.toDouble())
                        val aTangentDegreesVal =
                            aTanDegrees(radians.toDouble()).takeIf {
                                // Limita exibição de valores extremos
                                abs(it) < TANGENT_DISPLAY_LIMIT * 10 // Limite maior para graus
                            }
                        Text(text = "Asen: %.1f°".format(aSineDegreesVal), color = Color.Red)
                        Text(text = "Acos: %.1f°".format(aCosineDegreesVal), color = Color.Blue)
                        Text(text = "Atan: %.1f°".format(aTangentDegreesVal ?: Double.NaN), color = Color.Magenta)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InteractiveUnitCirclePreview() {
    InteractiveUnitCircle(modifier = Modifier.fillMaxSize())
}
