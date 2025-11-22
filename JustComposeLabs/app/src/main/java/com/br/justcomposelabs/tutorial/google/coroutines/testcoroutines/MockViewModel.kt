package com.br.justcomposelabs.tutorial.google.coroutines.testcoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/*
    https://developer.android.com/kotlin/coroutines/test?hl=pt-br
    https://developer.android.com/kotlin/flow/test?hl=pt-br
 */

class MockViewModel: ViewModel() {

    private val mutableString = MutableStateFlow("")

    val data: StateFlow<String> = mutableString.asStateFlow()

    fun fetchContent() {
        viewModelScope.launch {
            mutableString.value = "loading ..."
            delay(1000L)
            mutableString.value = "loaded"
        }
    }
}