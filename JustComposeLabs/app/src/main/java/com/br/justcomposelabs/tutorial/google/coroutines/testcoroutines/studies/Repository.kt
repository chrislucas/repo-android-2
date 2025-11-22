package com.br.justcomposelabs.tutorial.google.coroutines.testcoroutines.studies

import androidx.room.concurrent.AtomicBoolean
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
    https://developer.android.com/kotlin/coroutines/test
    --
    https://github.com/android/snippets/blob/07fc3540fe179a766d0b0a9e54a1898b526c4153/kotlin/src/main/kotlin/com/example/android/coroutines/testing/Repository.kt
 */


class Repository(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    private val scope = CoroutineScope(dispatcher)
    val initialized = AtomicBoolean(false)

    fun init() {
        scope.launch {
            initialized.set(true)
        }
    }

    suspend fun fetchData() : String = withContext(Dispatchers.IO) {
        ""
    }
}


class OtherRepository(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

}