package com.br.justcomposelabs.tutorial.google.compose.color.lerp

import android.R
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
    Animating a color with animateColorAsState

    Para uma mudança suave de cores, use animateColorAsState. Essa é a abordagem recomendada para
    animacao entre 2 estados de cores
 */

@Preview(showBackground = true)
@Composable
fun AnimatedColorExample() {
    var isToggled by remember { mutableStateOf(false) }

    val animatedColor by animateColorAsState(
        targetValue = if (isToggled) {
            Color.Red
        } else {
            Color.Green
        },
        label = "animatedColor"
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(animatedColor)
        )
        Button(
            onClick = {},
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Toggle Color")
        }
    }
}