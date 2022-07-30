package com.br.experience.funmobdatascience.features.share.http

import com.br.experience.funmobdatascience.features.share.models.Share
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface InvestmentAssetApi {

    @GET("/assets")
    fun getAssets(): Response<List<Share>>

    @GET("/asset/{id}")
    fun getAsset(@Path("id") id: Int): Response<Share>
}