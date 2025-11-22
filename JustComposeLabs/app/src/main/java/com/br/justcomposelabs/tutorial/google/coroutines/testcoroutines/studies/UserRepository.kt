package com.br.justcomposelabs.tutorial.google.coroutines.testcoroutines.studies

import kotlinx.coroutines.delay


data class User(val name: String, val email: String)
class UserRepository {

    private val users = mutableListOf<User>()

    suspend fun register(user: User) {
        delay(300L)
        users.add(user)
        println("$user registered")
    }

    suspend fun getAllUsers(): List<User> {
        delay(100L)
        return users
    }

}