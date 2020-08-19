package com.xp.samplemvvmarch.feature1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xp.samplemvvmarch.feature1.model.User
import com.xp.samplemvvmarch.feature1.repository.Repository


class UserProfileViewModelProviderFactory<E: Repository<LiveData<User?>>>(private val arg: E) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repository::class.java).newInstance(arg)
    }
}