package com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withcoroutines

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Usando RxJava2
 * https://medium.com/@nishargrocks007/using-retrofit-and-rxjava-in-your-android-project-8225f54df614
 */
interface SpTransportApi {

    @POST("/Login/Autenticar")
    suspend fun authentication(@Query("token") token: String): Response<Boolean>
}