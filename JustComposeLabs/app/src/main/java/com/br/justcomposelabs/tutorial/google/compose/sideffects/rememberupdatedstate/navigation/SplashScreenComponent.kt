package com.br.justcomposelabs.tutorial.google.compose.sideffects.rememberupdatedstate.navigation

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.R
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun SplashScreenComponent(onTimeout: () -> Unit = {}) {
    val currentOnTimeout by rememberUpdatedState(onTimeout)
    val scale = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        // Explicação do código de animação:
        // 1. scale.animateTo: Inicia uma animação assíncrona para alterar o valor do Animatable 'scale'.
        // 2. targetValue = 1f: O valor final da animação é 1.0 (escala normal).
        // 3. tween(durationMillis = 1000): Define uma animação baseada em tempo com duração de 1 segundo.
        // 4. easing: Define a curva de aceleração. Aqui usamos o OvershootInterpolator(2f) do Android
        //    clássico adaptado para o Compose. O valor '2f' (tension) faz com que a escala ultrapasse
        //    levemente o valor 1f e retorne, criando um efeito elástico de "pulo" ou "salto".
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(2000.milliseconds)
        currentOnTimeout()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            imageVector = Icons.Filled.Home,
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
        Text(text = stringResource(R.string.app_name))
    }
}