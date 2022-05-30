package com.experience.tutorial.flowlivedata.sa.utils

data class Resource<out T>(val status: Status, val data: T? = null, val message: String? = null) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(Status.Success, data)

        fun <T> error(message: String, data: T? = null) =
            Resource(Status.Error, data, message)

        fun <T> loading(data: T? = null) = Resource(Status.Loading, data)

        fun <T> failure(message: String, data: T? = null) =
            Resource(Status.Failure, data, message)
    }
}

sealed class Status {
    object Success : Status() {
        override fun toString(): String = "Success"
    }
    object Error : Status() {
        override fun toString(): String = "Error"
    }
    object Loading : Status() {
        override fun toString(): String = "Loading"
    }
    object Failure : Status() {
        override fun toString(): String = "Failure"
    }
}