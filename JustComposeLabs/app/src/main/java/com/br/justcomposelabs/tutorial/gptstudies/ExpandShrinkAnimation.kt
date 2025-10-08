package com.br.justcomposelabs.tutorial.gptstudies

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExpandShrinkAnimation() {
    // Create an infinite transition for continuous animation
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Use the animated scale inside a Column
    Column(
        modifier = Modifier
            .padding(16.dp)
            .size(100.dp * scale)
    ) {
        // Inside the Column, place a Row that also scales
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp * scale)
                    .background(MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(20.dp * scale)
                    .background(MaterialTheme.colorScheme.secondary)
            )
        }
    }
}

@Preview
@Composable
fun ExpandShrinkAnimationPreview() {
    ExpandShrinkAnimation()
}
