package com.br.justcomposelabs.tutorial.google.compose.sideffects.rememberupdatedstate.navigation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.R
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay

private val OvershootInterpolator = Easing { fraction ->
    val tension = 2f
    val t = fraction - 1f
    t * t * ((tension + 1f) * t + tension) + 1f
}


private val springAnimationSpec = spring(
    dampingRatio = Spring.DampingRatioMediumBouncy,
    stiffness = Spring.StiffnessLow,
    // Força a animação a encerrar quando faltar 0.05 para atingir o alpha final
    visibilityThreshold = 0.05f
)

@Suppress("EffectKeys")
@Composable
fun SplashScreenComponent(
    modifier: Modifier = Modifier,
    delay: Duration = 2000L.milliseconds,
    animationDurationMillis: Int = 1_000,
    icon: ImageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
    onTimeout: () -> Unit = {}
) {
    val currentOnTimeout by rememberUpdatedState(onTimeout)
    val scale = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        // Explicação do código de animação:
        // 1. scale.animateTo: inicia uma animação assíncrona para alterar o valor do Animatable 'scale'.
        // 2. targetValue = 1f: O valor final da animação é 1.0 (escala normal).
        // 3. tween(durationMillis = 1000): Define uma animação baseada em tempo com duração de 1 segundo.
        // 4. easing: Define a curva de aceleração. Aqui usamos o OvershootInterpolator(2f) do Android
        //    clássico adaptado para o Compose. O valor '2f' (tension) faz com que a escala ultrapasse
        //    levemente o valor 1f e retorne, criando um efeito elástico de "pulo" ou "salto".
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = animationDurationMillis,
                easing = OvershootInterpolator
            )
        )
        delay(delay)
        currentOnTimeout()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize(),
    ) {
        Image(
            imageVector = icon,
            contentDescription = "Logo",
            /*
                Analisar o Antes e depois

                Antes:
                Modifier.scale(scale.value)

                Depois:
                Modifier.graphicsLayer {
                    scaleX = scale.value
                    scaleY = scale.value
                }


             */
            modifier = Modifier.graphicsLayer {
                scaleX = scale.value
                scaleY = scale.value
            }
        )
        Text(
            text = stringResource(R.string.app_name),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenComponentPreview() {
    JustComposeLabsTheme {
        SplashScreenComponent()
    }
}
