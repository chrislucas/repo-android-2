package com.br.justcomposelabs.tutorial.coroutines.medium.statein

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn

/*
    https://medium.com/@mortitech/sharein-vs-statein-in-kotlin-flows-when-to-use-each-1a19bd187553
 */

class SharedRandomNumberFlow: ViewModel() {
    val sharedRandomNumberFlow = flow {
        while (true) {
            delay(1000)
            emit((0 .. 100).random())
        }
    }.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed()
    )
}


class StateIn: ViewModel() {

    /*
        Criar um exemplo com a doc abaixo
        https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/state-in.html
     */
}