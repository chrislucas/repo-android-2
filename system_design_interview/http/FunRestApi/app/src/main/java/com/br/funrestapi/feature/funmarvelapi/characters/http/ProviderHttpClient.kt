package com.br.funrestapi.feature.funmarvelapi.characters.http

import com.br.funrestapi.BuildConfig.MARVEL_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProviderHttpClient {

    fun provideCharacterApi() = provide<MarvelCharacterApi>(MARVEL_BASE_URL)

    private inline fun <reified Client> provide(baseUrl: String): Client =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Client::class.java)
}