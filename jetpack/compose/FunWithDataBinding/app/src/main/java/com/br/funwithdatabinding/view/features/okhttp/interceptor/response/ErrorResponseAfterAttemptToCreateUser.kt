package com.br.funwithdatabinding.view.features.okhttp.interceptor.response

import com.br.funwithdatabinding.view.features.okhttp.interceptor.MockHttpInterceptor
import com.br.restclientlib.ProviderEndpointClient.createOkHttpClientBuilder
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class ErrorResponseAfterAttemptToCreateUser : MockResponse {
    override fun createResponse(chain: Interceptor.Chain): Response {
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

fun createOkHttpClientWithFailureResultInterceptor(): OkHttpClient {
    return createOkHttpClientBuilder()
        .addInterceptor(MockHttpInterceptor(ErrorResponseAfterAttemptToCreateUser()))
        .build()
}