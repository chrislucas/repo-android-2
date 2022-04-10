package com.experience.tutorial.flowlivedata.sa.feature.fakeinterceptor.models

import com.experience.tutorial.BuildConfig
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

interface DelegateResponseBuilder {
    fun build(chain: Interceptor.Chain): Response
}

class SuccessResponseBuilderUserLoginRequest : DelegateResponseBuilder {
    override fun build(chain: Interceptor.Chain): Response {
        val body = "{\"message\": \"usuario adicionado\", \"status\": \"success\" }"
            .toResponseBody("application/json".toMediaTypeOrNull())
        return Response.Builder()
            .code(200)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(body)
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
            .addHeader("content-type", "application/json")
            .build()
    }
}

class FakeInterceptor(private val delegateResponseBuilder: DelegateResponseBuilder) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (BuildConfig.DEBUG) {
            delegateResponseBuilder.build(chain)
        } else {
            chain.proceed(chain.request())
        }
    }
}