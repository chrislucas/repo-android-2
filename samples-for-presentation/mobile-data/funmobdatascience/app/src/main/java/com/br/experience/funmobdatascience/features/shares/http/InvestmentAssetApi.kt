package com.br.experience.funmobdatascience.features.shares.http

import com.br.experience.funmobdatascience.features.portfolio.models.Portfolio
import com.br.experience.funmobdatascience.features.shares.models.Share
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface InvestmentAssetApi {

    @GET("/assets")
    fun getAssets(): Response<List<Share>>

    @GET("/portfolio/{userId}/{portfolioId}")
    fun getPortfolio(@Path("userId") userId: String, @Path("portfolioId") portfolioId: String): Response<Portfolio>

    @GET("/asset/{id}")
    fun getAsset(@Path("id") id: Int): Response<Share>

    @POST("/asset/buy/{userId}/{assetId}")
    fun buyAsset(@Path("userId") userId: String, @Path("assetId") assetId: String, @Body value: Double)

    @POST("/sell/buy/{userId}/{assetId}")
    fun sellAsset(@Path("userId") userId: String, @Path("assetId") assetId: String)
}