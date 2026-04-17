package com.br.justcomposelabs.tutorial.coroutines.kdoc.flow.throttle

import androidx.lifecycle.ViewModel
import com.br.justcomposelabs.tutorial.coroutines.kdoc.flow.throttleFirst
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class ThrottleFirstViewModel: ViewModel() {

    /*
        https://share.google/aimode/dWIksMfPyZU6UXVCO
     */
    val flow = flow {
        while (true) {
            val randomDelay = (100L..500L).random()
            val message = "Emitting with delay $randomDelay ms"
            Timber.tag("ThrottleFirstViewModel").d(message)
            delay(randomDelay)
            emit(message)
        }
    }.throttleFirst(1000L)

}