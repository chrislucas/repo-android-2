package com.br.justcomposelabs.tutorial.google.coroutines.testcoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/*
    https://waqasyounis334.medium.com/testing-coroutines-simplified-ace686331bda
 */
class AnotherMockViewModel: ViewModel() {
    private val mutableState = MutableStateFlow(false)
    val state: StateFlow<Boolean> = mutableState.asStateFlow()

    fun register() {
        viewModelScope.launch {
            mutableState.emit(true)
            delay(1000L)
            mutableState.emit(false)
        }
    }
}