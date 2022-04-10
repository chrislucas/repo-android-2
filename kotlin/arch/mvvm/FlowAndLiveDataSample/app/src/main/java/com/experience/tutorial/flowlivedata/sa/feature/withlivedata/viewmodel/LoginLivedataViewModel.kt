package com.experience.tutorial.flowlivedata.sa.feature.withlivedata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.experience.tutorial.flowlivedata.sa.feature.withlivedata.repositories.LoginLivedataRepository
import com.experience.tutorial.flowlivedata.sa.models.User
import com.experience.tutorial.flowlivedata.sa.network.model.LoginResponse
import com.experience.tutorial.flowlivedata.sa.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginLivedataViewModel(private val livedataRepository: LoginLivedataRepository): BaseViewModel() {

    private val loginData = MutableLiveData<Resource<LoginResponse>>()
    val observerLoginData: LiveData<Resource<LoginResponse>> = loginData

    fun login(user: User): Job {
        loginData.value = Resource.loading()
        return viewModelScope.launch {
            loginData.value = livedataRepository.login(user)
        }
    }
}