package com.br.experience.funmobdatascience.network.interceptor.samples

import com.br.experience.funmobdatascience.BuildConfig
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor

interface MockableResponseBuilder {
    fun build(chain: Interceptor.Chain, mockBody: () -> ResponseBody): Response
}

class SuccessResponseBuilderRequest : MockableResponseBuilder {
    override fun build(chain: Interceptor.Chain, mockBody: () -> ResponseBody): Response {
        return Response.Builder()
            .code(200)
            .request(chain.request())
            .protocol(Protocol.HTTP_2)
            .body(mockBody())
            .message("success")
            .addHeader("content-type", "application/json")
            .build()
    }
}

class FailureResponseBuilderRequest : MockableResponseBuilder {
    override fun build(chain: Interceptor.Chain, mockBody: () -> ResponseBody): Response {
        return Response.Builder()
            .code(500)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(mockBody())
            .message("failure")
            .addHeader("content-type", "application/json")
            .build()
    }
}

class HttpInterceptor(
    private val delegate: MockableResponseBuilder? = null, private val mockBody: (() -> ResponseBody)? = null
) : Interceptor {

    private val logger = HttpLoggingInterceptor.Logger { println(it) }

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (BuildConfig.DEBUG) {
            if (delegate != null && mockBody != null) {
                val request = chain.request()
                logger.log(
                    String.format(
                        "Request: %s\nConnection: %s\nHeaders: %s\n",
                        request.url, chain.connection() ?: "null", request.headers.toMultimap()
                    )
                )
                val response = delegate.build(chain, mockBody)
                logger.log(
                    String.format(
                        "Response: %s\nBody: %s\nHeaders: %s", response.request.url,
                        response.body, response.headers.toMultimap()
                    )
                )
                response
            } else {
                chain.proceed(chain.request())
            }
        } else {
            /**
             * https://square.github.io/okhttp/features/interceptors/
             */
            chain.proceed(chain.request())
        }
    }
}