package com.xp.samplemvvmarch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.xp.samplemvvmarch.model.User
import java.lang.IllegalArgumentException

class UserProfileViewModel : ViewModel() {
    lateinit var  user: LiveData<User>

    override fun onCleared() {
        super.onCleared()
        Log.i("USR_PROFILE_VIEW_MODEL", "Observando o metodo onCleared")
    }
}
