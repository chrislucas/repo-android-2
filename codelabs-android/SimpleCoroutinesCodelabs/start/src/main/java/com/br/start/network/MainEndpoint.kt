package com.br.start.network

import retrofit2.Call
import retrofit2.http.GET

interface MainEndpoint {

    @GET("next_title.json")
    fun fetchNextTitle(): Call<String>
}