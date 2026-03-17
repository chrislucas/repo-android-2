package com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.service

import android.util.Log
import com.br.restclientlib.ProviderEndpointClient
import com.br.restclientlib.utils.interceptor.RequestInterceptor
import com.br.restclientlib.utils.interceptor.ResponseInterceptor
import okhttp3.OkHttpClient

object SpTransportApiInterceptor {
    /*
        TODO solucionar o problema de cookie de secao da api olho vivo
        https://forum.processing.org/two/discussion/22588/http-requests-cookies-sptrans-olho-vivo.html
        TODO estudar se essa é a melhor estrategia
     */
    var cookies: Set<String> = mutableSetOf()

    fun addInterceptors(): OkHttpClient {
        val builder = ProviderEndpointClient.createOkHttpClientBuilderHandlerCookie()
        val sender = RequestInterceptor { request ->
            Log.i("RequestInterceptor", "$request")
        }
        val receiver = ResponseInterceptor { response ->
            Log.i("ResponseInterceptor", "$response")
            val setCookie = response.headers("Set-Cookie")
            if (setCookie.isNotEmpty()) {
                cookies = cookies union setCookie
            }
        }
        builder.addInterceptor(sender)
        builder.addInterceptor(receiver)
        return builder.build()
    }
}