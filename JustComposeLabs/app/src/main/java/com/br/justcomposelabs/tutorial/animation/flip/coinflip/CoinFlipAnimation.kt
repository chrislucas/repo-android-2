package com.br.justcomposelabs.tutorial.animation.flip.coinflip

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.justcomposelabs.R
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme
import com.br.justcomposelabs.utils.composable.paddingEdgeToEdge
import kotlinx.coroutines.launch
import kotlin.random.Random

/*
    TODO - terminar esse estudo
 */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CoinFlipAnimation() {
    var isFlipping by remember { mutableStateOf(false) }
    var showHeads by remember { mutableStateOf(true) }

    val rY by animateFloatAsState(
        targetValue = if (isFlipping) 360f else 0f,
        animationSpec = tween(durationMillis = 500, easing = LinearEasing),
        label = "rotationY"
    )


    LaunchedEffect(rY) {
        if (rY == 360f) {
            isFlipping = false
            showHeads = (0..1).random() == 0
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        /*
        Image(
            painter = painterResource(id = if(showHeads) R.drawable.head else R.drawable.tail),
            contentDescription = "Coin",
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer {
                    rotationY = rY
                    cameraDistance = 8f * density
                }.clickable {
                    if (!isFlipping) {
                        isFlipping = true
                    }
                }
        )

         */
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ClickableTimedFlippingBox() {
    var isClicked by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    // Usamos Animatable para ter controle programático sobre a rotação
    val rotationY = remember { Animatable(0f) }

    // Animação de cor simples baseada no estado de clique
    val animatedColor by animateColorAsState(
        targetValue = if (isClicked) Color.Blue else Color.Red,
        animationSpec = tween(durationMillis = 400),
        label = "colorAnimation"
    )

    // Ação de clique
    val onClick: () -> Unit = {
        if (!isClicked) {
            isClicked = true
            scope.launch {
                // Determina a direção e o destino final randomicamente (0 ou 1)
                val randomDirection = Random.nextBoolean()
                // Define o número de rotações completas em 10 segundos
                val rotationsPerSide = 15f
                val targetRotation = if (randomDirection) {
                    rotationY.value + (360f * rotationsPerSide) // Sentido horário
                } else {
                    rotationY.value - (360f * rotationsPerSide) // Sentido anti-horário
                }

                // Inicia a animação de rotação com duração de 10 segundos (10000ms) e easing suave
                rotationY.animateTo(
                    targetValue = targetRotation,
                    animationSpec = tween(
                        durationMillis = 10000,
                        easing = FastOutSlowInEasing // Começa rápido e desacelera no final
                    )
                )

                // Define isClicked de volta para falso após a conclusão da animação (10s depois)
                isClicked = false
            }
        }
    }

    Box(
        modifier = Modifier
            .size(200.dp)
            .systemBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
            .graphicsLayer {
                // Aplica a rotação animada ao redor do eixo Y
                this.rotationY = rotationY.value
                cameraDistance = 8 * density
            }
            .clickable(onClick = onClick)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = animatedColor)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .graphicsLayer {
                            // Contrarrotação para manter o círculo sempre visível e não invertido
                            this.rotationY = -rotationY.value
                        },
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CoinTossFlippingBox() {
    val scope = rememberCoroutineScope()
    // Animatable para controlar a rotação do eixo Y
    val rY = remember { Animatable(0f) }
    var isSpinning by remember { mutableStateOf(false) }

    // Face da moeda: True (Azul) ou False (Vermelho)
    val sideFaceTrue = Color.Blue
    val sideFaceFalse = Color.Red

    // Função de clique para iniciar o lançamento
    val onClick: () -> Unit = {
        if (!isSpinning) {
            isSpinning = true
            scope.launch {
                // 1. Decidir o resultado aleatório (True ou False)
                val finalResultIsBlue = Random.nextBoolean()

                // 2. Definir o destino final em graus.
                // A moeda deve girar por várias voltas e parar em 0 (True) ou 180 (False).
                // Adicionamos um grande número de voltas para a animação parecer realista.
                val numberOfSpins = 15 // 15 voltas completas
                val targetDegrees = if (finalResultIsBlue) {
                    // Parar na face 0 graus (True)
                    360f * numberOfSpins
                } else {
                    // Parar na face 180 graus (False)
                    (360f * numberOfSpins) + 180f
                }

                // 3. Animar para o destino por 10 segundos com easing de desaceleração
                rY.animateTo(
                    targetValue = targetDegrees,
                    animationSpec = tween(
                        durationMillis = 10000, // 10 segundos
                        // Este easing imita o atrito, começando rápido e parando suavemente
                        easing = FastOutSlowInEasing
                    )
                )

                // 4. Resetar o estado de giro após 10 segundos
                isSpinning = false
            }
        }
    }

    Box(
        modifier = Modifier
            .size(200.dp)
            .graphicsLayer {
                this.rotationY = rY.value
                // Adiciona perspectiva para melhor efeito 3D
                cameraDistance = 8 * density
            }
            .clickable(onClick = onClick, enabled = !isSpinning)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Face da frente (True side)
        FlipCardFace(color = sideFaceTrue, text = "TRUE")
        // Face de trás (False side) - esta será "virada" usando graphicsLayer
/*
        Box(
            modifier = Modifier
                .graphicsLayer {
                    // Esta face é virada 180 graus inicialmente para trás
                    rotationY = 180f
                }
        ) {
            FlipCardFace(color = sideFaceFalse, text = "FALSE")
        }

 */
    }
}


@Composable
fun FlipCardFace(color: Color, text: String) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // O círculo interno foi removido para focar na funcionalidade de duas faces
            Text(
                text = text,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewFlipCardFace() {
    JustComposeLabsTheme {
        FlipCardFace(color = Color.Blue, text = "TRUE")
    }
}

