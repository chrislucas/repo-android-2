package com.xp.samplemvvmarch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xp.samplemvvmarch.model.User
import com.xp.samplemvvmarch.repository.Repository


class FactoryUserProfileViewModel<E: Repository<LiveData<User?>>>(private val arg: E) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repository::class.java).newInstance(arg)
    }
}