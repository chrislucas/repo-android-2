package com.br.funrestapi.feature.funmarvelapi.characters.http

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelCharacterApi {

    /*
        TODO
        estudar formas seguras de armazenar chaves de api armazenamento
        FIREBASE
        NDK LIBRARY

        Exemplo de request
        https://developer.marvel.com/documentation/authorization
     */
    @GET("/v1/public/characters")
    suspend fun getCharactersWithoutFilter(
        @Query("ts") timestamp: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Response<Any>

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: String,
        @Query("ts") timestamp: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Response<Any>
}