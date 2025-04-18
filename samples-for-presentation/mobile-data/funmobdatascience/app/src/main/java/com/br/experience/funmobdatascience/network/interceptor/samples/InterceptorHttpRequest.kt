package com.br.experience.funmobdatascience.network.interceptor.samples

import com.br.experience.funmobdatascience.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

interface DelegateResponseBuilder {
    fun build(chain: Interceptor.Chain): Response
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

class InterceptorHttpRequest(private val delegateResponseBuilder: DelegateResponseBuilder) : Interceptor {

    private val logger = HttpLoggingInterceptor.Logger { println(it) }

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (BuildConfig.DEBUG) {
            val request = chain.request()
            logger.log(
                String.format(
                    "Request: %s\nConnection: %s\nHeaders: %s\n",
                    request.url, chain.connection() ?: "null", request.headers.toMultimap()
                )
            )
            val response = delegateResponseBuilder.build(chain)
            logger.log(
                String.format(
                    "Response: %s\nBody: %s\nHeaders: %s", response.request.url,
                    response.body, response.headers.toMultimap()
                )
            )
            response
        } else {
            /**
             * https://square.github.io/okhttp/features/interceptors/
             */
            chain.proceed(chain.request())
        }
    }
}
