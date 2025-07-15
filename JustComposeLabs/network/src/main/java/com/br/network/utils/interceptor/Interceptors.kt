package com.br.network.utils.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


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