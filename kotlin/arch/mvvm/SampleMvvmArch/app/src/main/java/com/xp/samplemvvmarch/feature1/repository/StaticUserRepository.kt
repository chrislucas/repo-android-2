package com.xp.samplemvvmarch.feature1.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xp.samplemvvmarch.feature1.model.User


/**
 * Repositorio estatico de um modelo de dados com objetivo de estudar os componentes
 * da arquitetura MVVM
 *
 *
 *
 *
 * */
class StaticUserRepository(private val id: Int) : Repository<LiveData<User?>> {

    companion object {
        val map = mapOf(1 to User(1, "Christoffer")
            , 2 to User(2, "James")
            , 3 to User(3, "Anna")
            , 4 to User(4, "Carlos")
        )
    }

    // Fake
    override fun get(): LiveData<User?> {
        return MutableLiveData(map[id])
    }



}