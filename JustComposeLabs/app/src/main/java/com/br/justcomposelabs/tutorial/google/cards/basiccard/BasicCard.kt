package com.br.justcomposelabs.tutorial.google.cards.basiccard

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable

/*
    https://developer.android.com/develop/ui/compose/quick-guides/content/create-card-as-container#basic
 */
@Composable
fun WhiteLabelBasicCard(content: @Composable () -> Unit) {
    Card {
        content()
    }
}


