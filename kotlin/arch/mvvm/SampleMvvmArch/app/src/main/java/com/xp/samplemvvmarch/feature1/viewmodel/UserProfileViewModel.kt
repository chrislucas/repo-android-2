package com.xp.samplemvvmarch.feature1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xp.samplemvvmarch.feature1.model.User
import com.xp.samplemvvmarch.feature1.repository.Repository

class UserProfileViewModel(private val repository: Repository<LiveData<User>>) : ViewModel() {

    var userLiveData = MutableLiveData<User>()

    override fun onCleared() {
        super.onCleared()
        Log.i("USR_PROFILE_VIEW_MODEL", "Observando o metodo onCleared")
    }


    fun get() {
        userLiveData.value = repository.get().value
    }
}
