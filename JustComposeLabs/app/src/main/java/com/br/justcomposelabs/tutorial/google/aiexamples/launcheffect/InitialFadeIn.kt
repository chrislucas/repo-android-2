package com.br.justcomposelabs.tutorial.google.aiexamples.launcheffect

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/*
    Launched used in animation compose

    LaunchedEffect used in animation compose
 */



@Preview(showBackground = true)
@Composable
fun AnimatedTextContent() {
    var visibility by remember { mutableStateOf (false) }

    AnimatedContent(
        targetState = visibility,
        label = "visibility",
        transitionSpec = {
            fadeIn(animationSpec = tween(300)) togetherWith fadeOut(animationSpec = tween(300))
        }
    ) { s ->
        Text("Target State: $s")
    }

    Button(onClick = { visibility = !visibility }) {
        Text("Toggle Visibility")
    }
}

/**
 *  AnimatedContent fadeIn and fadeOut
 */

@Preview(showBackground = true)
@Composable
fun AnimatedContentFadeDemo() {
    var isFirstContent by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(
            targetState = isFirstContent,
            transitionSpec = {
                // Combine the enter and exit animations using the `with` infix function.
                fadeIn(animationSpec = tween(300)) togetherWith fadeOut(animationSpec = tween(300))
            }, label = "AnimatedContent Fade"
        ) { targetState ->
            if (targetState) {
                Text(
                    text = "First Content",
                    style = MaterialTheme.typography.headlineMedium
                )
            } else {
                Text(
                    text = "Second Content",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { isFirstContent = !isFirstContent }) {
            Text("Switch Content")
        }
    }
}