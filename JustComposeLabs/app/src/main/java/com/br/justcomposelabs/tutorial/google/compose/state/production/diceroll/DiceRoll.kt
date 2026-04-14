package com.br.justcomposelabs.tutorial.google.compose.state.production.diceroll

/*
    One-shot APIs as sources of state change
    https://developer.android.com/topic/architecture/ui-layer/state-production#one-shot-apis
 */
data class DiceUiState(
    val firstDieValue: Int? = null,
    val secondDieValue: Int? = null,
    val numberOfRolls: Int = 0,
)
