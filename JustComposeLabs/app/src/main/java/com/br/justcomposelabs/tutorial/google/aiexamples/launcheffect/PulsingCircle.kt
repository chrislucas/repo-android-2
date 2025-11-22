package com.br.justcomposelabs.tutorial.google.aiexamples.launcheffect

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.isActive

@Preview(showBackground = true)
@Composable
fun PulsingCircle() {
    /*
        LaunchedEffect used in animation compose
     */
    val scale = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        // LaunchedEffect(Unit) ensures it runs once on initial composition
        while (isActive) { // isActive checks if the coroutine is still active
            scale.animateTo(1.2f, animationSpec = tween(durationMillis = 500))
            scale.animateTo(1f, animationSpec = tween(durationMillis = 500))
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.Companion.size(100.dp)) {
            drawCircle(
                color = Color(0xFFB388FF),
                radius = size.minDimension / 2 * scale.value
            )
        }
    }
}