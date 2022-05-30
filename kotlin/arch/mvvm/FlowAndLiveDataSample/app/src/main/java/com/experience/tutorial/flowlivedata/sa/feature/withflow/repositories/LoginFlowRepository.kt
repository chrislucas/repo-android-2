package com.experience.tutorial.flowlivedata.sa.feature.withflow.repositories

import com.experience.tutorial.flowlivedata.sa.models.User
import com.experience.tutorial.flowlivedata.sa.network.LoginEndpoint
import com.experience.tutorial.flowlivedata.sa.network.model.LoginResponse
import com.experience.tutorial.flowlivedata.sa.utils.BaseDataSource
import com.experience.tutorial.flowlivedata.sa.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginFlowRepository(private val loginEndpoint: LoginEndpoint) : BaseDataSource() {

    suspend fun login(user: User): Flow<Resource<LoginResponse>> {
        return flow {
            val result = safeApiCall { loginEndpoint.login(user) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}