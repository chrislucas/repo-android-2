package com.br.features.tutorials.google.flowtest.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class Repository() {

    /**
     * https://developer.android.com/kotlin/flow/test#continuous-collection
     * Continuous collection during a test
     */

    fun score() : Flow<Int> = flow {

    }
}


class FakeDataSource {

    private val flow = MutableSharedFlow<Int>()
    suspend fun emit(value: Int) = flow.emit(value)

}