package com.br.restclientlib.utils

import com.google.gson.Gson
import retrofit2.Response

object SafeServiceCall {

    inline fun <T, reified E> callService(
        call: () -> Response<T>,
        onError: (Throwable?) -> E
    ): Resource<T, E> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let { Resource.success(it) } ?: Resource.failure(onError(null))
            } else {
                val message: E = Gson().fromJson(response.errorBody()!!.charStream(), E::class.java)
                Resource.error(message)
            }
        } catch (e: Exception) {
            Resource.failure(onError(e))
        }
    }
}