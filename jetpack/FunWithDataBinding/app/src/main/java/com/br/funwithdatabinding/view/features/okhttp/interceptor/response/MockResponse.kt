package com.br.funwithdatabinding.view.features.okhttp.interceptor.response

import okhttp3.Interceptor
import okhttp3.Response

interface MockResponse {
    fun createResponse(chain: Interceptor.Chain): Response
}