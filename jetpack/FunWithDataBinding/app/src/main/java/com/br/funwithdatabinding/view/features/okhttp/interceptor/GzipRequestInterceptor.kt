package com.br.funwithdatabinding.view.features.okhttp.interceptor

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.BufferedSink
import okio.GzipSink
import okio.buffer

/**
 * https://square.github.io/okhttp/features/interceptors/#rewriting-requests
 *
 * Com interceptor conseguimos adicionare, remover ou trocar headers de request. Podemos tambem transformar
 * o corpo de uma requisicao que possui um.
 *
 * O exemplo abaixo adiciona uma compressao no corpo ad requesicao se estivermos conectados a um webserver que suporta
 * tao operacao
 */
class GzipRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val rq = chain.request()
        val newRequest: Request? = rq.body?.let { requestBody ->
            rq.newBuilder()
                .header("Content-Encoding", "gzip")
                .method(rq.method, gzip(requestBody))
                .build()
        }
        return newRequest?.let { chain.proceed(it) } ?: chain.proceed(rq)
    }

    private fun gzip(requestBody: RequestBody): RequestBody {
        return object : RequestBody() {
            override fun contentType(): MediaType? = requestBody.contentType()

            override fun writeTo(sink: BufferedSink) {
                val buffer = GzipSink(sink).buffer()
                requestBody.writeTo(buffer)
                buffer.close()
            }
        }
    }
}