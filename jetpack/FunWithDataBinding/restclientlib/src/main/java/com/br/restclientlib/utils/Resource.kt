package com.br.restclientlib.utils

data class Resource<out T, out E>(val status: Status, val data: T? = null, val message: E? = null) {
    companion object {
        fun <T> success(data: T): Resource<T, Nothing> =
            Resource(Status.Success, data)

        fun <T, E> error(message: E, data: T? = null) =
            Resource(Status.Error, data, message)

        fun <T> loading(data: T? = null) = Resource(Status.Loading, data, null)

        fun <T, E> failure(message: E, data: T? = null) =
            Resource(Status.Failure, data, message)
    }
}

sealed class Status {
    data object Success : Status()
    data object Error : Status()
    data object Loading : Status()
    data object Failure : Status()
}