package com.br.funwithjetpackcompose.tutorials.medium.testing.testinginjetpackcompose.ui.components

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.tooling.preview.Preview

private const val DividerLengthInDegrees = 1.8f


@Composable
fun AnimatedCircle(
    modifier: Modifier = Modifier,
    proportions: List<Float>,
    colors: List<Color>,
) {

    val currentState = remember {
        MutableTransitionState(AnimatedCircleProgress.START)
    }
}


private enum class AnimatedCircleProgress { START, END }

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewAnimatedCircle() {
    AnimatedCircle(
        modifier = Modifier.fillMaxWidth(),
        listOf(),
        listOf(Color(0xFF80D8FF), Color(0xFFB388FF), Color(0xFFA7FFEB))
    )
}