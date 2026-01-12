package com.br.justcomposelabs.tutorial.google.cards.elevetadcard

import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable

/*
    https://developer.android.com/develop/ui/compose/quick-guides/content/create-card-as-container
 */


@Composable
fun WhitelabelElevatedCard(content: @Composable () -> Unit) {

    ElevatedCard {
        content()
    }
}