package com.br.features.tutorials.google.flowtest.producer

import kotlinx.coroutines.flow.flow

class ProducerString {

    /**
     * Fonte
     * https://developer.android.com/kotlin/flow/test#producer
     *
     * Asserting flow emissions in a test
     * https://developer.android.com/kotlin/flow/test#assert
     */
    fun producerString() = flow {
        emit("Hello World")
    }
}