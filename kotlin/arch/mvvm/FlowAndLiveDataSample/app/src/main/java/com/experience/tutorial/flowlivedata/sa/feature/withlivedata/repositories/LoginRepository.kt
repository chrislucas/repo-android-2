package com.experience.tutorial.flowlivedata.sa.feature.withlivedata.repositories

import com.experience.tutorial.flowlivedata.sa.models.User
import com.experience.tutorial.flowlivedata.sa.network.LoginEndpointHelper
import com.experience.tutorial.flowlivedata.sa.network.model.LoginResponse
import com.experience.tutorial.flowlivedata.sa.utils.BaseDataSource
import com.experience.tutorial.flowlivedata.sa.utils.Resource

/**
 * https://proandroiddev.com/flow-livedata-what-are-they-best-use-case-lets-build-a-login-system-39315510666d
 * https://medium.com/swlh/how-i-built-a-simple-currency-converter-app-using-recommended-android-pattern-and-architecture-204a3bbfc142
 * */
class LoginRepository(private val loginHelper: LoginEndpointHelper) : BaseDataSource() {

    suspend fun login(user: User): Resource<LoginResponse> {
        return safeApiCall { loginHelper.login(user) }
    }
}