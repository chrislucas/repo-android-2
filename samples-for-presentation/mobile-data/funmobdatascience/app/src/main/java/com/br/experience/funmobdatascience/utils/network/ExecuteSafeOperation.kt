package com.br.experience.funmobdatascience.utils.network

import com.br.experience.funmobdatascience.utils.viewmodel.Operation
import retrofit2.Response

object ExecuteSafeOperation {

    suspend fun <T> safeRequest(request: suspend () -> Response<T>, onError: (Response<T>) -> String): Operation<T> {
        return try {
            val response = request()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Operation.success(body)
                } else {
                    Operation.failure("Null Response")
                }
            } else {
                Operation.error(onError(response))
            }
        } catch (e: Exception) {
            Operation.failure("Error ${e.message}")
        }
    }
}