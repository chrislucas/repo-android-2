package com.experience.tutorial.flowlivedata.sa.network

import com.experience.tutorial.flowlivedata.sa.models.User

/**
 * TODO: talvez essa classe possa ser eliminada
 */
class LoginEndpointHelper(private val endpoint: LoginEndpoint) {
    suspend fun login(user: User) = endpoint.login(user)
}