package com.experience.tutorial.flowlivedata.sa.feature.withflow.viewmodel

import androidx.lifecycle.viewModelScope
import com.experience.tutorial.flowlivedata.sa.feature.withflow.repositories.LoginFlowRepository
import com.experience.tutorial.flowlivedata.sa.feature.utils.viewmodel.BaseViewModel
import com.experience.tutorial.flowlivedata.sa.models.User
import com.experience.tutorial.flowlivedata.sa.network.ProviderEndpointClient
import com.experience.tutorial.flowlivedata.sa.network.model.LoginResponse
import com.experience.tutorial.flowlivedata.sa.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginFlowViewModel : BaseViewModel() {
    private val loginUserFlow = Channel<Resource<LoginResponse>>(Channel.BUFFERED)
    val observerLoginUserFlow = loginUserFlow.receiveAsFlow()

    private val loginFlowRepository: LoginFlowRepository =
        LoginFlowRepository(ProviderEndpointClient.mockLoginEndpointSuccess())

    fun login(user: User): Job =
        viewModelScope.launch {
            loginUserFlow.send(Resource.loading())
            loginFlowRepository.login(user)
                .catch { exception ->
                    loginUserFlow.send(Resource.error(exception.message ?: ""))
                }
                .collect { resource ->
                    loginUserFlow.send(resource)
                }
        }
}