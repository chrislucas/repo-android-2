package com.br.justcomposelabs.tutorial.google.uilayerlibraries.tutorial.dicerollersample

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


/*
    https://medium.com/bumble-tech/crash-course-on-the-android-ui-layer-part-1-2094221a9be3
 */


/*
    Producing UI state with local source of state change
 */
data class DiceRollUiState(
    val firstDiceValue: Int? = null,
    val secondDiceValue: Int? = null,
    val numberOfRolls: Int = 0
)

interface RandomProvider {
    fun nextInt(range: IntRange): Int
}

class DiceRollStateHolder(
    private val randomProvider: RandomProvider
) {

    private val _uiState = MutableStateFlow(DiceRollUiState())
    /*
       Por que usar o metodo asStateFlow()?
       https://stackoverflow.com/questions/71276772/why-do-the-author-need-to-use-asstateflow-in-compose
       O metodo asStateFlow prove uma instancia de uma subclasse de SharedStateFlow chamada ReadonlyStateFlow
            - Obs: StateFlow ja e uma subclasse de SharedStateFlow
       A ReadonlyStateFlow nao expoe os metodos que alteram o estado, como
       value e compareAndSet, garantindo que o estado so possa ser alterado
       dentro da classe que o possui (DiceRollStateHolder neste caso).
       Isso promove o encapsulamento e previne alteracoes indesejadas

       public fun <T> MutableStateFlow<T>.asStateFlow(): StateFlow<T> =
    ReadonlyStateFlow(this, null)

     */
    val uiState: StateFlow<DiceRollUiState> = _uiState.asStateFlow()

    fun rollDice() {
        _uiState.update { currentState ->
            currentState.copy(
                firstDiceValue = randomProvider.nextInt(1..6),
                secondDiceValue = randomProvider.nextInt(1..6),
                numberOfRolls = currentState.numberOfRolls + 1
            )
        }
    }
}