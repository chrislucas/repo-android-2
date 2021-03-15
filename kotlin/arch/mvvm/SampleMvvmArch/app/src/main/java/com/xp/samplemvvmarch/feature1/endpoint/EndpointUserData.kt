package com.xp.samplemvvmarch.feature1.endpoint

import com.xp.samplemvvmarch.feature1.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndpointUserData {

    @GET("users/{user_id}")
    fun get(@Path("user_id") userId: Int) : Call<User>
}