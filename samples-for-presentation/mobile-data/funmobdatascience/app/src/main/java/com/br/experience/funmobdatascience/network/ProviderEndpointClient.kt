package com.br.experience.funmobdatascience.network

import com.br.experience.funmobdatascience.BuildConfig
import com.br.experience.funmobdatascience.features.share.http.InvestmentAssetApi
import com.br.experience.funmobdatascience.network.interceptor.samples.HttpInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProviderEndpointClient {

    fun provideInvestmentAssetApi(httpInterceptor: HttpInterceptor): InvestmentAssetApi =
        provideEndpoint(BuildConfig.INVESTMENT_BASE_URL, httpInterceptor)

    private inline fun <reified C> provideEndpoint(baseUrl: String, httpInterceptor: HttpInterceptor): C {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClientWithInterceptor(httpInterceptor))
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

    private fun createOkHttpClientWithInterceptor(httpInterceptor: HttpInterceptor): OkHttpClient {
        return createOkHttpClientDefault()
            .addInterceptor(httpInterceptor)
            .build()
    }
}