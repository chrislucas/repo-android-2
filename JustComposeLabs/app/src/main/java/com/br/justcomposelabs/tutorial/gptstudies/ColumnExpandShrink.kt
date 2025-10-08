package com.br.justcomposelabs.tutorial.gptstudies

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColumnExpandShrink() {
    // Controla o valor de expansão
    val transition = rememberInfiniteTransition()
    val heightFraction by transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // Elemento 1
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp * heightFraction)
                .background(Color.Red)
        )
        // Elemento 2
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp * heightFraction)
                .background(Color.Green)
        )
        // Elemento 3
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp * heightFraction)
                .background(Color.Blue)
        )
    }
}

@Preview
@Composable
fun ColumnExpandShrinkPreview() {
    ColumnExpandShrink()
}
