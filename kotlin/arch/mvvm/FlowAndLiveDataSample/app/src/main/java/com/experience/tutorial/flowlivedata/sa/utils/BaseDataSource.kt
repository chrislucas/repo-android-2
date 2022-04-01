package com.experience.tutorial.flowlivedata.sa.utils

import com.experience.tutorial.flowlivedata.sa.network.model.LoginResponse
import com.google.gson.Gson
import retrofit2.Response

abstract class BaseDataSource {

    suspend fun <T> safeApiCall(call: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Resource.success(body)
                }
                else {
                    Resource.failure("Null Response")
                }
            } else {
                val message: LoginResponse =
                    Gson().fromJson(response.errorBody()!!.charStream(), LoginResponse::class.java)
                Resource.error(message.message)
            }
        } catch (e: Exception) {
            Resource.failure("Error ${e.message}")
        }
    }
}