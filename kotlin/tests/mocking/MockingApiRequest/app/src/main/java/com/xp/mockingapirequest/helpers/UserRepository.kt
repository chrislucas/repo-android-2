package com.xp.mockingapirequest.helpers

import com.xp.mockingapirequest.models.User

class UserRepository(private val factory: UserFactory = UserFactory(0, "")) {

    constructor(id: Int, name: String) : this(UserFactory(id, name))

    fun createUser(id: Int, name: String) : User = User(id, name)

    class UserFactory(val userId: Int, val name: String) {
        fun create() : User = User(userId, name)
    }

}