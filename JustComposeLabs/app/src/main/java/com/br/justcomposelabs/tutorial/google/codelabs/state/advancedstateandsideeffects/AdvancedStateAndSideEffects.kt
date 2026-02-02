package com.br.justcomposelabs.tutorial.google.codelabs.state.advancedstateandsideeffects

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/*
    https://developer.android.com/codelabs/jetpack-compose-advanced-state-side-effects
 */


/*
    A pergunta
    using explicity StateFlow<T> is not enough to make sure that uiState is read only
 */


class UiUnsafeState : ViewModel() {
    // Private mutable backing property (only ViewModel can change)
    private val _uiState = MutableStateFlow(0)

    /*
        O teste é verificar se é possivel fazer o casting para um MutableStateFlow
        fora da classe UiUnsafeState, o que permitiria alterar o valor de uiState
        diretamente.
     */
    // Public read-only property for the UI
    val uiState: StateFlow<Int> = _uiState//.asStateFlow()

    fun updateData(operation: () -> Int) {
        // Logic to update the state
        _uiState.value = operation()
    }
}


fun test() {
    val uiUnsafeState = UiUnsafeState()
    val unsafeUiState = uiUnsafeState.uiState as MutableStateFlow<Int>
    unsafeUiState.value = 10 // This will compile and run, modifying the state directly
}