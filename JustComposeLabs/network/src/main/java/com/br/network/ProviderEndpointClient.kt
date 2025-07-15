package com.br.network

import com.squareup.leakcanary.core.BuildConfig
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.util.concurrent.TimeUnit
import kotlin.apply
import kotlin.jvm.java

object ProviderEndpointClient {

    inline fun <reified T> createService(
        url: String,
        converterFactory: Converter.Factory = GsonConverterFactory.create(),
        createOkHttpClient: () -> OkHttpClient = ::createOkHttpClient
    ): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(createOkHttpClient())
            .addConverterFactory(converterFactory)
            .build()
            .create(T::class.java)
    }

    inline fun <reified T> createCompatRxJava2Service(
        url: String,
        converterFactory: Converter.Factory = GsonConverterFactory.create(),
        createOkHttpClient: () -> OkHttpClient = ::createOkHttpClient
    ): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(createOkHttpClient())
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(T::class.java)
    }


    inline fun <reified T> createReactiveService(
        url: String,
        converterFactory: Converter.Factory = GsonConverterFactory.create(),
        createOkHttpClient: () -> OkHttpClient = ::createOkHttpClient
    ): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(createOkHttpClient())
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(T::class.java)
    }

    fun createClientHandlerCookie(): OkHttpClient {
        return createOkHttpClientBuilderHandlerCookie().build()
    }

    fun createOkHttpClientBuilderHandlerCookie(): OkHttpClient.Builder {
        val cookieHandler = CookieManager()
        val builder = OkHttpClient.Builder()
        builder.cookieJar(JavaNetCookieJar(cookieHandler))
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        builder.addInterceptor(interceptor)
        return builder
    }

    fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(logging)
            }
        }.build()
    }

    fun createOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(logging)
            }
        }
    }
}