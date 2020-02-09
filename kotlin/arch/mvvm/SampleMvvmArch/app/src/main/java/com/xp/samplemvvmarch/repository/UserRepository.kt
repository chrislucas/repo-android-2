package com.xp.samplemvvmarch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xp.samplemvvmarch.model.User

class UserRepository {

    fun getUser(userId: Int) : LiveData<User> {
        val data = MutableLiveData<User>()

        return data
    }

}