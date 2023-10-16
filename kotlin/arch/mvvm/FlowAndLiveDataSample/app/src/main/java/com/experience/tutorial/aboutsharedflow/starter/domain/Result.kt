package com.experience.tutorial.aboutsharedflow.starter.domain



sealed class Result<out T> {

    data class Success<T>(val value: T): Result<T>()

    data class Failure(val cause: Throwable): Result<Nothing>()

    fun get(): T = when(this) {
        is Success -> value
        is Failure -> {
            throw cause
        }
    }
}

inline fun <T> fetch(fn: () -> T): Result<T> = try {
    Result.Success(fn())
} catch (e: Throwable) {
    Result.Failure(e)
}

