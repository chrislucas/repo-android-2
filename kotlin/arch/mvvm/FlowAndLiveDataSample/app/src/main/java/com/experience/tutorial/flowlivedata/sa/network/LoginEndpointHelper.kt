package com.experience.tutorial.flowlivedata.sa.network

import com.experience.tutorial.flowlivedata.sa.models.User

class LoginEndpointHelper(private val endpoint: LoginEndpoint) {
    suspend fun login(user: User) = endpoint.login(user)
}