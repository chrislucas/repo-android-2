package com.br.justcomposelabs.tutorial.coroutines.kdoc.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/*
    https://share.google/aimode/dWIksMfPyZU6UXVCO

    In Kotlin Coroutines, throttleFirst is an operator that
    immediately emits the first value and then suppresses all subsequent v
    alues for a specified time window.

    It is primarily used to prevent "double-clicks" or rapid event bursts in UI programming
 */

fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> = flow {
    var lastEmissionTime = 0L
    collect { value ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastEmissionTime >= windowDuration) {
            lastEmissionTime = currentTime
            emit(value)
        }
    }
}
