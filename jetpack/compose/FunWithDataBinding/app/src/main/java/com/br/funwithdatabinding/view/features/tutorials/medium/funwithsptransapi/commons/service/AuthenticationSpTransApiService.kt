package com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.service

import androidx.fragment.app.FragmentActivity
import com.br.funwithdatabinding.view.features.utils.FirebaseRemoteConfig


interface CallbackAuthenticationSpTransApi {
    fun onErrorFetchToken()
    fun onError(exception: Throwable)
    fun onSuccess(status: Boolean)
}


interface CallbackOnHttpRequest<T> {
    fun onException(exception: Throwable)
    fun onSuccess(value: T)
    fun onFail()
}

object AuthenticationSpTransApiService {

    fun fetchToken(
        context: FragmentActivity,
        callback: ((token: String?) -> Unit)? = null
    ): String? {
        val firebaseRemoteConfig = FirebaseRemoteConfig()
        var token: String? = null
        firebaseRemoteConfig.fetchAndActivate(context) { task ->
            if (task.isSuccessful) {
                token = firebaseRemoteConfig.spTransToken
                callback?.invoke(token)
            }
        }
        return token
    }

    /*
        Desse jeito nao passamos pelo ViewModel

        fun authenticate(
            context: FragmentActivity,
            callbackAuthenticationSpTransApi: CallbackAuthenticationSpTransApi
        ) {
            fetchToken(context)?.let { token ->
                val service = RxSpTransportAuthenticationService()
                service.authenticateInSpTransApi(
                    token,
                    onError = callbackAuthenticationSpTransApi::onError,
                    onSuccess = callbackAuthenticationSpTransApi::onSuccess
                )
            } ?: run {
                callbackAuthenticationSpTransApi.onErrorFetchToken()
            }
        }

     */
}