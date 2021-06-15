package com.br.start.network

import com.br.start.utils.SkipNetworkInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MainNetwork {

    private val endpoint: MainEndpoint by lazy {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(SkipNetworkInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MainEndpoint::class.java)
    }

    fun getMainEndpoint() = endpoint
}