package com.br.experience.funmobdatascience.network.interceptor.finassets

import com.br.experience.funmobdatascience.network.interceptor.samples.DelegateResponseBuilder
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

class SuccessResponseBuilderStockMarketShare(private val responseBody: ResponseBody) : DelegateResponseBuilder {
    override fun build(chain: Interceptor.Chain): Response {
        return Response.Builder()
            .code(200)
            .request(chain.request())
            .protocol(Protocol.HTTP_2)
            .body(responseBody)
            .message("success")
            .addHeader("content-type", "application/json")
            .build()
    }
}