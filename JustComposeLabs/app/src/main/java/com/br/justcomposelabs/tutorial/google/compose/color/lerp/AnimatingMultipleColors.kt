package com.br.justcomposelabs.tutorial.google.compose.color.lerp

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateSize
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


private enum class AnimationState { Start, End }


@Preview(showBackground = true)
@Composable
fun UpdateTransitionColorExample() {
    var currentState by remember { mutableStateOf(AnimationState.Start) }

    val transition = updateTransition(currentState, label = "colorTransition")

    var sliderPosition by remember { mutableFloatStateOf(0f) }

    val startColor = Color(0xFF68ACE0)
    val endColor = Color(0xFFA7FFEB)

    val interpolatedColor = lerp(
        startColor,
        endColor,
        sliderPosition
    )


    val animatedColor by transition.animateColor(label = "box_color") { state ->
        when (state) {
            AnimationState.Start -> startColor
            AnimationState.End -> endColor
        }
    }

    val animatedSize by transition.animateSize(label = "box_size") { state ->
        when (state) {
            AnimationState.Start -> Size(100f, 100f)
            AnimationState.End -> Size(200f, 200f)
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(animatedSize.width.dp, animatedSize.height.dp)
                .background(animatedColor)
        )

        Button(onClick = {
            currentState =
                if (currentState == AnimationState.Start) AnimationState.End else AnimationState.Start
        }) {
            Text("Animate Color and Size")
        }

        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..1f,
            modifier = Modifier.padding(16.dp)
        )
    }
}