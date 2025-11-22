package com.br.network.utils.interceptor

import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit


/*
    https://stackoverflow.com/questions/63130930/retrofit-interface-how-to-add-cookies-to-store-data
 */
class RequestInterceptor(private val callback: (Request) -> Unit) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val original: Request = chain.request()
        callback(original)
        return chain.proceed(builder.build())
    }
}

class ResponseInterceptor(private val callback: (Response) -> Unit) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        callback(originalResponse)
        return originalResponse
    }
}

/*
    caching responses retrofit
 */

val cacheInterceptor = Interceptor { chain ->
    val response = chain.proceed(chain.request())
    val cacheControl = CacheControl.Builder()
        .maxAge(5, TimeUnit.MINUTES)
        .build()

    response.newBuilder()
        .header("Cache-Control", cacheControl.toString())
        .build()
}

val okHttpClient = OkHttpClient.Builder()
    .addNetworkInterceptor(cacheInterceptor)
    //.cache(Cache())
    .build()