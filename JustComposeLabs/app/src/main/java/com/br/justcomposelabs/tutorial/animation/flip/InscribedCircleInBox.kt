package com.br.justcomposelabs.tutorial.animation.flip

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InteractiveBoxWithCircle() {
    // State for managing the color and the flipped status
    var isFlipped by remember { mutableStateOf(false) }
    val targetColor by remember { mutableStateOf(Color.Blue) }
    val initialColor by remember { mutableStateOf(Color.Red) }
    val currentColor by animateColorAsState(
        targetValue = if (isFlipped) targetColor else initialColor,
        animationSpec = tween(durationMillis = 500), label = "colorAnimation"
    )

    // State for managing the rotation animation
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 500, easing = LinearEasing), label = "rotationAnimation"
    )

    Box(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(Color(0xFFFDF8F8)) // The outer box
            .clickable { isFlipped = !isFlipped }, // Add click listener to the whole box
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                // Apply rotation animation using graphicsLayer
                .graphicsLayer {
                    rotationY = rotation
                    // optional: add perspective for a better 3D effect
                    cameraDistance = 8f * density
                }
                .clip(CircleShape) // Draw a circle shape
                .background(currentColor), // Apply the animated color
            contentAlignment = Alignment.Center
        ) {
            // Text to show the flip effect
            Text(
                text = if (rotation <= 90f) "Front" else "Back",
                color = Color.White,
                // Ensure text on the "back" side is also flipped correctly
                modifier = Modifier.graphicsLayer {
                    rotationY = if (rotation <= 90f) 0f else 180f
                }
            )
        }
    }
}