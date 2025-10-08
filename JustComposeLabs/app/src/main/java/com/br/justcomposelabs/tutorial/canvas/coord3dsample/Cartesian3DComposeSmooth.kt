package com.br.justcomposelabs.tutorial.canvas.coord3dsample

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

@Preview(showBackground = true, showSystemUi = false, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun Cartesian3DComposeSmooth() {
    // Controle de rotação com animação suave
    val rotationXAnim = remember { Animatable(0f) }
    val rotationYAnim = remember { Animatable(0f) }

    // Variáveis de rotação atuais
    var rotationX by remember { mutableFloatStateOf(0f) }
    var rotationY by remember { mutableFloatStateOf(0f) }

    // Escopo da corrotina para animações
    val scope = rememberCoroutineScope()

    // Canvas com deteção de gestos de arrasto
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()

                    val targetX = rotationX + dragAmount.y * 0.01f
                    val targetY = rotationY + dragAmount.x * 0.01f

                    // Anima a rotação suavemente usando o escopo da corrotina
                    scope.launch {
                        rotationXAnim.animateTo(targetX, animationSpec = tween(durationMillis = 300))
                        rotationYAnim.animateTo(targetY, animationSpec = tween(durationMillis = 300))
                        rotationX = rotationXAnim.value
                        rotationY = rotationYAnim.value
                    }
                }
            }
    ) {
        val width = size.width
        val height = size.height
        val centerX = width / 2
        val centerY = height / 2

        val spaceSize = 300f  // Tamanho do eixo

        // Pontos finais dos eixos 3D
        val axes = listOf(
            floatArrayOf(spaceSize, 0f, 0f),   // X positivo
            floatArrayOf(-spaceSize, 0f, 0f),  // X negativo
            floatArrayOf(0f, spaceSize, 0f),   // Y positivo
            floatArrayOf(0f, -spaceSize, 0f),  // Y negativo
            floatArrayOf(0f, 0f, spaceSize),   // Z positivo
            floatArrayOf(0f, 0f, -spaceSize)   // Z negativo
        )
        val colors = listOf(Color.Red, Color.Red, Color.Green, Color.Green, Color.Blue, Color.Blue)

        // Desenhando os eixos
        for (i in axes.indices step 2) {
            val startXYZ = axes[i]
            val endXYZ = axes[i + 1]

            // Aplicar rotação
            val startRot = rotatePoint(startXYZ, rotationX, rotationY)
            val endRot = rotatePoint(endXYZ, rotationX, rotationY)

            // Projeção 3D para 2D
            val start2D = projectPoint(startRot, size.width, size.height, centerX, centerY)
            val end2D = projectPoint(endRot, size.width, size.height, centerX, centerY)

            // Desenho do eixo
            drawLine(
                color = colors[i],
                start = start2D,
                end = end2D,
                strokeWidth = 4f
            )
        }
    }
}

/**
 * rotatePoint realiza rotação de um ponto 3D ao redor dos eixos X e Y.
 * @param point O ponto 3D como FloatArray(x, y, z)
 * @param rotX Ângulo de rotação ao redor do eixo X, em radianos
 * @param rotY Ângulo de rotação ao redor do eixo Y, em radianos
 * @return O ponto rotacionado como FloatArray(x, y, z)
 */
fun rotatePoint(point: FloatArray, rotX: Float, rotY: Float): FloatArray {
    val (x, y, z) = point

    // Rotação em torno do eixo X
    val y1 = y * cos(rotX) - z * sin(rotX)
    val z1 = y * sin(rotX) + z * cos(rotX)

    // Rotação em torno do eixo Y
    val x2 = x * cos(rotY) + z1 * sin(rotY)
    val z2 = -x * sin(rotY) + z1 * cos(rotY)

    return floatArrayOf(x2, y1, z2)
}

/**
 * projectPoint projeta um ponto 3D para uma coordenada 2D na tela.
 * Usa uma projeção em perspectiva básica, considerando uma distância da câmera.
 * @param point3D O ponto 3D como FloatArray(x, y, z)
 * @param screenWidth Largura da tela (ou canvas)
 * @param screenHeight Altura da tela (ou canvas)
 * @param centerX Ponto central X na tela
 * @param centerY Ponto central Y na tela
 * @param distance Distância da câmera até o plano de projeção (padrão 500f)
 * @return Offset (x, y) na tela
 */
fun projectPoint(
    point3D: FloatArray,
    screenWidth: Float,
    screenHeight: Float,
    centerX: Float,
    centerY: Float,
    distance: Float = 500f // profundidade da câmera
): Offset {
    val (x, y, z) = point3D

    // Calcula o fator de escala com perspectiva
    val scale = distance / (distance + z)

    // Aplica escala ao ponto (projeção perspectiva)
    val projectedX = centerX + x * scale
    val projectedY = centerY + y * scale

    return Offset(projectedX, projectedY)
}
