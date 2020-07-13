package com.xp.samplemvvmarch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xp.samplemvvmarch.model.User

class LocalUserRepository(private val id: Int) : Repository<LiveData<User?>> {

    companion object {
        val map = mapOf(1 to User(1, "chrisluccas"), 2 to User(2, "Jamess"))
    }

    // Fake
    override fun get(): LiveData<User?> {
        return MutableLiveData(User(1, "chrisluccas"))
    }



}