package com.xp.samplemvvmarch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.xp.samplemvvmarch.model.User
import java.lang.IllegalArgumentException

class UserProfileViewModel (saveStateHandler: SavedStateHandle) : ViewModel() {
    lateinit var  user: LiveData<User>
}
