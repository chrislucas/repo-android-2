package com.br.justcomposelabs.tutorial.google.performance.followbestpractice

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.State

@Composable
private fun ScrollToTopButton(onClick: () -> Unit = {}) = Unit


@Composable
private fun animateColorBetween(first: Color, second: Color): State<Color> {
    return remember { mutableStateOf(first) }
}