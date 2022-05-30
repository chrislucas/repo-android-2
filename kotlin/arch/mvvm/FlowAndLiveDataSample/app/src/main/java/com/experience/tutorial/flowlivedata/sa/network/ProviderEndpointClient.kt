package com.experience.tutorial.flowlivedata.sa.network

import com.experience.tutorial.BuildConfig
import com.experience.tutorial.flowlivedata.sa.feature.fakeinterceptor.models.ErrorResponseBuilderUserLoginRequest
import com.experience.tutorial.flowlivedata.sa.feature.fakeinterceptor.models.FailureResponseBuilderUserLoginRequest
import com.experience.tutorial.flowlivedata.sa.feature.fakeinterceptor.models.FakeInterceptor
import com.experience.tutorial.flowlivedata.sa.feature.fakeinterceptor.models.SuccessResponseBuilderUserLoginRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProviderEndpointClient {

    fun mockLoginEndpointSuccess(): LoginEndpoint = mockSuccessLogin(BuildConfig.LOGIN_ENDPOINT)

    fun mockLoginEndpointFailure(): LoginEndpoint {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.LOGIN_ENDPOINT)
            .client(createOkHttpClientWithFailureResultInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginEndpoint::class.java)
    }

    fun mockLoginEndpointError(): LoginEndpoint {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.LOGIN_ENDPOINT)
            .client(createOkHttpClientWithErrorResultInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginEndpoint::class.java)
    }

    private inline fun <reified C> mockSuccessLogin(baseUrl: String): C {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClientWithSuccessResultInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(C::class.java)
    }

    private fun createOkHttpClientDefault(): OkHttpClient.Builder {
        return OkHttpClient.Builder().run {
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
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

    private fun createOkHttpClientWithFailureResultInterceptor(): OkHttpClient {
        return createOkHttpClientDefault()
            .addInterceptor(FakeInterceptor(FailureResponseBuilderUserLoginRequest()))
            .build()
    }

    private fun createOkHttpClientWithErrorResultInterceptor(): OkHttpClient {
        return createOkHttpClientDefault()
            .addInterceptor(FakeInterceptor(ErrorResponseBuilderUserLoginRequest()))
            .build()
    }
}