package com.br.wrapperrestclient

import com.experience.tutorial.flowlivedata.sa.feature.fakeinterceptor.models.ErrorResponseBuilderUserLoginRequest
import com.experience.tutorial.flowlivedata.sa.feature.fakeinterceptor.models.FailureResponseBuilderUserLoginRequest
import com.experience.tutorial.flowlivedata.sa.feature.fakeinterceptor.models.FakeInterceptor
import com.experience.tutorial.flowlivedata.sa.feature.fakeinterceptor.models.SuccessResponseBuilderUserLoginRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProviderService {

    inline fun <reified T> mockFailureService(baseUrl: String): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClientWithFailureResultInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }

    inline fun <reified T> mockErrorService(baseUrl: String): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClientWithErrorResultInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }

    private inline fun <reified C> mockSuccessLogin(baseUrl: String): C {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClientWithSuccessResultInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(C::class.java)
    }

    private fun createOkHttpClientDefault(debuggable: Boolean = false): OkHttpClient.Builder {
        return OkHttpClient.Builder().run {
            val logging = HttpLoggingInterceptor()
            if (debuggable) {
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            addInterceptor(logging)
        }
    }

    private fun createOkHttpClientWithSuccessResultInterceptor(): OkHttpClient {
        return createOkHttpClientDefault()
            .addInterceptor(FakeInterceptor(SuccessResponseBuilderUserLoginRequest()))
            .build()
    }

    fun createOkHttpClientWithFailureResultInterceptor(): OkHttpClient {
        return createOkHttpClientDefault()
            .addInterceptor(FakeInterceptor(FailureResponseBuilderUserLoginRequest()))
            .build()
    }

    fun createOkHttpClientWithErrorResultInterceptor(): OkHttpClient {
        return createOkHttpClientDefault()
            .addInterceptor(FakeInterceptor(ErrorResponseBuilderUserLoginRequest()))
            .build()
    }
}