package com.experience.tutorial.flowlivedata.sa.network

import com.experience.tutorial.flowlivedata.sa.models.User
import com.experience.tutorial.flowlivedata.sa.network.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Entrypoint e Endpoint
 * https://stackoverflow.com/questions/53199289/rest-api-entry-point-and-endpoint
 *
 * Entrypoint
 * http://api.your-company.com
 *
 * Endpoint
 * http://api.your-company.com/login
 * http://api.your-company.com/feature1
 * http://api.your-company.com/feature2
 * http://api.your-company.com/featuren
 *
 * */

interface LoginEndpoint {

    @POST("")
    suspend fun login(@Body user: User): Response<LoginResponse>
}