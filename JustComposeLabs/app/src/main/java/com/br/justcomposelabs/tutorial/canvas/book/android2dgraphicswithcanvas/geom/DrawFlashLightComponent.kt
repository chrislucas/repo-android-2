package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.geom

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

/**
 * https://developer.android.com/develop/ui/compose/graphics/draw/modifiers#drawwithcontent
 */

@Composable
fun FlashlightLayout(
    modifier: Modifier = Modifier,
    component: @Composable () -> Unit
) {
    /*
        https://share.google/aimode/1O5pqULUEIEckLnLO
     */

    var pointerOffset by remember { mutableStateOf(Offset.Zero) }
    var isTouching by remember { mutableStateOf(false) }

    // Anima o raio da lanterna (cresce quando toca, some quando solta)
    val radius by animateFloatAsState(
        targetValue = if (isTouching) 450f else 0.1f,
        animationSpec = tween(durationMillis = 400),
        label = "flashlightRadius"
    )

    Box(
        modifier = modifier
            .pointerInput(Unit) {
                // Atualiza a posição da lanterna conforme o toque/arraste
                detectDragGestures(
                    onDragStart = { isTouching = true },
                    onDragEnd = { isTouching = false },
                    onDragCancel = { isTouching = false }
                ) { change, _ ->
                    pointerOffset = change.position
                }
            }
            .drawWithContent {
                /**
                 * @see drawContent
                 *
                 * Desenha o Box
                 */
                drawContent()
                /*
                                Para remover esse comentário o código que controla
                                a animação do raio do ciruclo deve ser

                                if (isTouching) 450f else 0f // else deve ser 0f

                                if (radius > 0.0f) {
                                    drawRect(
                                        Brush.radialGradient(
                                            0.0f to Color.Transparent,
                                            0.5f to Color.Black.copy(alpha = 0.9f), // Transição suave
                                            center = pointerOffset,
                                            radius = radius
                                        )
                                    )
                                }
                        - Aqui desenhamos um retângulo escuro que cobre a tela
                        toda e com a cor preta para esconder o conteúdo passado
                        por argumento

                        - O Brush é usado para desenhar um gradiente de cores por uma lista. A última
                        cor da lista é a cor usada para desenhar a forma
                            - no exemplo abaixo o centro Brush:
                                - é definido no valor 0.0f e terá a cor transparent
                                - ao chegar a 30% mantém transparente
                                - depois disso gradativamente vai ficando escuro, até um
                                preto com um alpha de 85%, quanto mais perto do 1 mais escuro
                            - 0.0f é o centro, 1.0f é o limite do raio definido
                            - Efeito de preenchimento
                                - Como o drawRect cobre a tela toda, o gradiente, após atingir o ponto 1.0f,
                                continua a preencher o restante do retângulo com a última cor definida (o preto translúcido).

                 */

                drawRect(
                    Brush.radialGradient(
                        // 0.0f é o centro, 1.0f é o limite do raio definido
                        colorStops = arrayOf(
                            0.0f to Color.Transparent,
                            0.3f to Color.Transparent, // Mantém o centro totalmente limpo até 30% do raio
                            1.0f to Color.Black.copy(alpha = 0.85f), // Esfumaça até chegar no preto 85% opaco
                        ), // Transição suave
                        center = pointerOffset,
                        radius = radius
                    )
                )
            },
        contentAlignment = Alignment.Center
    ) {
        component()
    }
}

@Preview(showBackground = true)
@Composable
fun FlashlightScreen() {
    FlashlightLayout {
        // Você pode colocar qualquer UI aqui dentro
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "CONTEÚDO ESCONDIDO",
                style = TextStyle(
                    fontSize = 30.sp,
                    color = Color(0xFFEA683F),
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}
