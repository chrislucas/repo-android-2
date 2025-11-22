package com.br.justcomposelabs.tutorial.google.coroutines.testcoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnotherMockViewModelVersionII(): ViewModel() {

    private val mutableString = MutableStateFlow("")

    val data: StateFlow<String> = mutableString.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            mutableString.value = "loading..."
            delay(1000L)
            mutableString.value = "loaded."
        }
    }
}