package com.experience.tutorial.flowlivedata.sa.feature.fakeinterceptor.models

import com.experience.tutorial.BuildConfig
import java.util.logging.Logger
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor

interface DelegateResponseBuilder {
    fun build(chain: Interceptor.Chain): Response
}

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

/**
 * https://square.github.io/okhttp/features/interceptors/#application-interceptors
 *  - interceptors sao registrados como APPLICATION ou NETWORK
 *  - Registramos um APPLICATION interceptor chamando o metodo addInterceptor da classe OkHttpClient
 *  - Fazemos da mesma forma para NETWORK interceptor
 *      - No exemplo da documentacao o log disparado pelo interceptor eh chamado 2x
 *      - Isso ocorre por o exemplo uusa uma URL cujo scheme eh http e ocorre um redirect para https
 *      - O interceptador de NETWORK REQUEST contem mais dados
 *          - Accept-Enconding: #####
 *
 *
 */

class FakeInterceptor(private val delegateResponseBuilder: DelegateResponseBuilder) : Interceptor {

    val logger = HttpLoggingInterceptor.Logger {
        println(it)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (BuildConfig.DEBUG) {
            val request = chain.request()
            logger.log(String.format(
                "Request: %s\nConnection: %s\nHeaders: %s\n",
                request.url, chain.connection() ?: "null", request.headers.toMultimap()
            ))
            val response = delegateResponseBuilder.build(chain)
            logger.log(String.format(
                "Response: %s\nBody: %s\nHeaders: %s", response.request.url,
                response.body, response.headers.toMultimap()
            ))
            response
        } else {
            /**
             * https://square.github.io/okhttp/features/interceptors/
             */
            chain.proceed(chain.request())
        }
    }
}