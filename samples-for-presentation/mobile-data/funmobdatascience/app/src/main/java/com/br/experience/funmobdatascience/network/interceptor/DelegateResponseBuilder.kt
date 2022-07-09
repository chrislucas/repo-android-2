package com.br.experience.funmobdatascience.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

interface DelegateResponseBuilder {
    fun build(chain: Interceptor.Chain): Response
}