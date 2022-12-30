package com.br.funrestapi.feature.funmarvelapi.characters.repositories

import com.br.funrestapi.BuildConfig.MARVEL_KEY_API
import com.br.funrestapi.BuildConfig.MARVEL_PRIVATE_KEY_API
import com.br.funrestapi.feature.funmarvelapi.characters.http.MarvelCharacterApi
import com.br.funrestapi.utils.ExecuteSafeOperation
import com.br.funrestapi.utils.models.Operation
import java.security.MessageDigest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class MarvelCharacterRepository(private val api: MarvelCharacterApi) {

    private fun getHash(): Pair<Long, String> {
        val timestamp = System.currentTimeMillis()
        val digest = MessageDigest.getInstance("MD5")
        val buffer: ByteArray = digest.digest(
            "$timestamp$MARVEL_PRIVATE_KEY_API$MARVEL_KEY_API".toByteArray()
        )
        val hash = StringBuilder()
        for (byte in buffer) {
            hash.append(String.format("%02x", byte))
        }

        return Pair(timestamp, hash.toString())
    }

    private suspend fun <T> FlowCollector<Operation<T>>.executeAndEmmitResult(operation: suspend () -> Response<T>) {
        val result = ExecuteSafeOperation.safeRequest(operation) { response ->
            "${response.errorBody()}"
        }
        emit(result)
    }

    suspend fun getCharactersWithoutFilter(): Flow<Operation<Any>> =
        flow {
            val operation = suspend {
                val (timestamp, hash) = getHash()
                api.getCharactersWithoutFilter("$timestamp", MARVEL_KEY_API, hash)
            }
            executeAndEmmitResult(operation)

        }.flowOn(Dispatchers.IO) // https://kotlinlang.org/docs/flow.html#flowon-operator


    suspend fun getCharacterById(id: String): Flow<Operation<Any>> =
        flow {
            val operation = suspend {
                val (timestamp, hash) = getHash()
                api.getCharacterById(id, "$timestamp", MARVEL_KEY_API, hash)
            }
            executeAndEmmitResult(operation)
        }.flowOn(Dispatchers.IO)
}