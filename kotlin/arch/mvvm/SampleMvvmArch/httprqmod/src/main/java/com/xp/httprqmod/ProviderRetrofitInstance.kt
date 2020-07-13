package com.xp.httprqmod


import retrofit2.Converter
import retrofit2.Retrofit


object ProviderRetrofitInstance {


    @JvmStatic
    fun <T> get(clazz: Class<T>, convertFactory: Converter.Factory, baseURL: String): T =
        getInstance(convertFactory, baseURL).create(clazz)


    @JvmStatic
    private fun getInstance(convertFactory: Converter.Factory, baseURL: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(convertFactory)
            .baseUrl(baseURL)
            .build()
    }

}