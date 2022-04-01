package com.experience.tutorial.flowlivedata.sa.network

import com.experience.tutorial.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProviderEndpointClient {

    fun provide(): LoginEndpoint = provide("https://localhost:3001/login")

    inline fun <reified C> provide(baseUrl: String): C {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(C::class.java)
    }

    fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().run {
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            addInterceptor(logging)
            build()
        }
    }
}