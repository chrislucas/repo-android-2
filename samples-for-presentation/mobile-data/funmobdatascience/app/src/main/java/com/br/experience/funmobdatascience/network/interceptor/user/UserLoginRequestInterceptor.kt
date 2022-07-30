package com.br.experience.funmobdatascience.network.interceptor.user

import com.br.experience.funmobdatascience.network.interceptor.samples.DelegateResponseBuilder
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class SuccessResponseBuilderUserLoginRequest : DelegateResponseBuilder {
    override fun build(chain: Interceptor.Chain): Response {
        val body = "{\"message\": \"usuario adicionado\", \"status\": \"success\"}"
            .toResponseBody("application/json".toMediaTypeOrNull())
        return Response.Builder()
            .code(200)
            .request(chain.request())
            .protocol(Protocol.HTTP_2)
            .body(body)
            .message("success")
            .addHeader("content-type", "application/json")
            .build()
    }
}

class SuccessResponseBuilderDoingUserLoginRequest : DelegateResponseBuilder {
    override fun build(chain: Interceptor.Chain): Response {
        val body = "{\"message\": \"usuario adicionado\", \"status\": \"success\"}"
            .toResponseBody("application/json".toMediaTypeOrNull())
        val response = chain.proceed(chain.request())
        return response.newBuilder()
            .code(200)
            .request(chain.request())
            .protocol(Protocol.HTTP_2)
            .body(body)
            .message("success")
            .addHeader("content-type", "application/json")
            .build()
    }
}

class FailureResponseBuilderUserLoginRequest : DelegateResponseBuilder {
    override fun build(chain: Interceptor.Chain): Response {
        val body = "{\"message\": \"usuario nao foi adicionado\", \"status\": \"failure\" }"
            .toResponseBody("application/json".toMediaTypeOrNull())
        return Response.Builder()
            .code(500)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(body)
            .message("failure")
            .addHeader("content-type", "application/json")
            .build()
    }
}

class ErrorResponseBuilderUserLoginRequest : DelegateResponseBuilder {
    override fun build(chain: Interceptor.Chain): Response {
        val body = "{\"message\": \"usuario nao foi adicionado\", \"status\": \"error\" }"
            .toResponseBody("application/json".toMediaTypeOrNull())
        return Response.Builder()
            .code(500)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(body)
            .message("error")
            .addHeader("content-type", "application/json")
            .build()
    }
}