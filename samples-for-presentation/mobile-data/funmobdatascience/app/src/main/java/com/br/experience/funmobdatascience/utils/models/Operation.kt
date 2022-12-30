package com.br.experience.funmobdatascience.utils.models

data class Operation<out T>(val status: Status, val data: T? = null, val message: String? = null) {
    companion object {

        fun <T> idle(): Operation<T> = Operation(Status.IDLE, null, "idle")

        fun <T> success(data: T): Operation<T> =
            Operation(Status.Success, data)

        fun <T> error(message: String, data: T? = null) =
            Operation(Status.Error, data, message)

        fun <T> loading(data: T? = null) = Operation(Status.Loading, data)

        fun <T> failure(message: String, data: T? = null) =
            Operation(Status.Failure, data, message)
    }
}

sealed class Status {

    object IDLE : Status() {
        override fun toString(): String = "IDLE"
    }

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