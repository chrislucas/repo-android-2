package com.br.funwithdatabinding.view.features.okhttp.interceptor.response

import com.br.funwithdatabinding.view.features.okhttp.interceptor.MockHttpInterceptor
import com.br.restclientlib.ProviderEndpointClient.createOkHttpClientBuilder
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class SuccessResponseAfterCreateUser : MockResponse {
    override fun createResponse(chain: Interceptor.Chain): Response {
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


fun createOkHttpClientWithSuccessResultInterceptor(): OkHttpClient {
    return createOkHttpClientBuilder()
        .addInterceptor(MockHttpInterceptor(SuccessResponseAfterCreateUser()))
        .build()
}