package com.experience.tutorial.flowlivedata.sa.feature.withlivedata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.experience.tutorial.flowlivedata.sa.feature.utils.viewmodel.BaseViewModel
import com.experience.tutorial.flowlivedata.sa.feature.withlivedata.repositories.LoginLivedataRepository
import com.experience.tutorial.flowlivedata.sa.models.User
import com.experience.tutorial.flowlivedata.sa.network.ProviderEndpointClient
import com.experience.tutorial.flowlivedata.sa.network.model.LoginResponse
import com.experience.tutorial.flowlivedata.sa.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginLivedataViewModel() : BaseViewModel() {
    private val loginData = MutableLiveData<Resource<LoginResponse>>()
    val observerLoginData: LiveData<Resource<LoginResponse>> = loginData

    constructor(livedataRepository: LoginLivedataRepository): this() {
        this.livedataRepository = livedataRepository
    }

    private var livedataRepository: LoginLivedataRepository =
        LoginLivedataRepository(ProviderEndpointClient.mockLoginEndpointSuccess())

    fun login(user: User): Job {
        loginData.value = Resource.loading()
        return viewModelScope.launch {
            loginData.value = livedataRepository.login(user)
        }
    }
}