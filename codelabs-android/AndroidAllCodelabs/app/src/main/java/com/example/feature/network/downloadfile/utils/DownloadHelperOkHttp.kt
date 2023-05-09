package com.example.feature.network.downloadfile.utils

import java.time.Duration
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

suspend fun downloadFile(url: String, fn: (Response) -> Unit) {

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(Duration.ofMillis(100))
        .readTimeout(Duration.ofMillis(100))
        .build()

    flow<Response> {
        okHttpClient
            .newCall(Request.Builder().url(url).build())
            .execute()
    }.collect {
        fn(it)
    }


}