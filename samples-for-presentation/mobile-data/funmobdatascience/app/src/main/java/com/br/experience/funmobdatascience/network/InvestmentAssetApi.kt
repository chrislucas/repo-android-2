package com.br.experience.funmobdatascience.network

import com.br.experience.funmobdatascience.models.InvestmentAsset
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface InvestmentAssetApi {

    @GET("/assets")
    fun getAssets(): Response<List<InvestmentAsset>>

    @GET("/asset/{id}")
    fun getAsset(@Path("id") id: Int): Response<InvestmentAsset>
}