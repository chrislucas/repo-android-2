package com.br.funwithlifecycle.samples.diceroll

import androidx.lifecycle.ViewModel

/*
    https://developer.android.com/topic/libraries/architecture/viewmodel
 */
data class DiceUiState(
    val firstDieValue: Int? = null,
    val secondDieValue: Int? = null,
    val numberOfRolls: Int = 0,
)

class DiceRollViewModel: ViewModel() {
}
