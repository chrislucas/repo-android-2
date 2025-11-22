package com.br.justcomposelabs.tutorial.google.aiexamples.launcheffect

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview


@Preview(showBackground = true)
@Composable
fun AnimatedText(isVisible: Boolean = false) {
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(isVisible) {
        if (isVisible) {
            alpha.animateTo(1f, animationSpec = tween(durationMillis = 500))
        } else {
            alpha.animateTo(0f, animationSpec = tween(durationMillis = 500))
        }
    }

    Text("Hello, Compose!", modifier = Modifier.alpha(alpha.value))
}


