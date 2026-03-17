package com.br.wrapperrestclient


import com.google.gson.Gson
import retrofit2.Response
import java.io.InputStreamReader

object BaseDataSource {
    inline fun <reified T> safeApiCall(call: () -> Response<T>): Resource<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Resource.success(body)
                } else {
                    Resource.failure("Failure Response")
                }
            } else {
                val message = Gson().fromJson(
                    response.errorBody()?.charStream(), InputStreamReader::class.java
                )
                Resource.error(message.toString())
            }
        } catch (e: Exception) {
            Resource.failure("Error ${e.message}")
        }
    }
}