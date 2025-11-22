package com.br.network.utils.ktor

import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.serialization.gson.gson
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException

val httpClient = HttpClient(Android) {
    /*
        Client engine
        https://ktor.io/docs/client-engines.html

        Content negotiation and serialization in Ktor Client
        https://ktor.io/docs/client-serialization.html

     */
    install(ContentNegotiation) {
        /*
            Configure a serializer
            https://ktor.io/docs/client-serialization.html#configure_serializer
         */
        gson()
    }
    
    install(HttpTimeout) {
        requestTimeoutMillis = 15000L
        connectTimeoutMillis = 15000L
    }
}

/*
    https://stackoverflow.com/questions/54679592/handling-exception-in-httpclient-ktor/71801998#71801998
 */

sealed class ApiResponse<out T, out E> {
    data class Success<T>(val data: T) : ApiResponse<T, Nothing>()
    sealed class Error<E> : ApiResponse<Nothing, E>() {
        data class HttpError<E>(val code: Int, val errorBody: E?) : Error<E>()
        object NetworkError : Error<Nothing>()
        object SerializationError : Error<Nothing>()
    }
}


suspend inline fun <reified T, reified E> HttpClient.safeRequest(
    block: HttpRequestBuilder.() -> Unit,
): ApiResponse<T, E> =
    try {
        val response = request { block() }
        ApiResponse.Success(response.body())
    } catch (e: ClientRequestException) {
        ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
    } catch (e: ServerResponseException) {
        ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
    } catch (e: IOException) {
        ApiResponse.Error.NetworkError
    } catch (e: SerializationException) {
        ApiResponse.Error.SerializationError
    }

suspend inline fun <reified E> ResponseException.errorBody(): E? =
    try {
        response.body()
    } catch (e: SerializationException) {
        null
    }