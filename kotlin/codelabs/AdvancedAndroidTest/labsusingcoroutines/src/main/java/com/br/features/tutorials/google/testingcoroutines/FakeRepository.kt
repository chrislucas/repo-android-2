package com.br.features.tutorials.google.testingcoroutines

import kotlinx.coroutines.delay

/**
 * https://developer.android.com/kotlin/coroutines/test
 */
class FakeRepository {

    suspend fun fetch(): String {
        delay(1000L)
        return "Hello"
    }
}