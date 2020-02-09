package com.xp.samplemvvmarch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xp.samplemvvmarch.model.User

class LocalUserRepository(private val id: Int) : Repository<LiveData<User>> {

    // Fake
    override fun get(): LiveData<User> {
        return MutableLiveData(User(1, "chrisluccas"))
    }

}