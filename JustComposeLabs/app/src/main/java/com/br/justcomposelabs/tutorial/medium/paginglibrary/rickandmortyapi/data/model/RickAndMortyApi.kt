package com.br.justcomposelabs.tutorial.medium.paginglibrary.rickandmortyapi.data.model


import retrofit2.http.GET
import retrofit2.http.Query

/*
    https://medium.com/android-dev-br/paging-v3-jetpack-compose-2899a6877bef
 */
interface RickAndMortyApi {

    @GET("api/character/")
    suspend fun getCharactersByPage(@Query("page") page: Int)

    @GET("api/location/")
    suspend fun getLocation()

    @GET("api/episode/")
    suspend fun getEpisode()
}


data class Character(val name: String)