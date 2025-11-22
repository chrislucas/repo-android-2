package com.br.justcomposelabs.tutorial.medium.animation.flip.cardflip.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.tutorial.medium.animation.flip.cardflip.models.Card

@Composable
fun Stack(modifier: Modifier = Modifier) {

}

@Composable
fun StackLayout(
    modifier: Modifier = Modifier,
    flipcard: Card?,
    leftStack: @Composable () -> Unit,
    rightStack: @Composable () -> Unit
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {

    }
}