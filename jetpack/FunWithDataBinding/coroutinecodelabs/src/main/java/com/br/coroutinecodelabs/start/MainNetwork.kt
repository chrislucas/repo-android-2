package com.br.coroutinecodelabs.start

import com.br.coroutinecodelabs.start.utils.SkipNetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

private val service : Network by lazy {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost/")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .addInterceptor(SkipNetworkInterceptor())
                .addInterceptor {
                    /*
                        https://square.github.io/okhttp/features/interceptors/
                     */
                    val request = it.request()
                    val response = it.proceed(request)

                    response
                }
                .build()
        )
        .build()


    retrofit.create(Network::class.java)
}



interface Network {
    @GET("content_sample.json")
    fun fetch(): Call<String>
}