package com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.service

import androidx.fragment.app.FragmentActivity
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava2.features.authentication.services.RxSpTransportAuthenticationService
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

object FirebaseConfigRepository {

    fun fetchToken(
        context: FragmentActivity,
        callback: ((token: String?) -> Unit)? = null
    ) {
        val firebaseRemoteConfig = FirebaseRemoteConfig()
        firebaseRemoteConfig.fetchAndActivate(context) { task ->
            if (task.isSuccessful) {
                callback?.invoke(firebaseRemoteConfig.spTransToken)
            }
        }
    }


    @Deprecated("")
    fun fetchDeprecated(
        context: FragmentActivity,
        callbackAuthenticationSpTransApi: CallbackAuthenticationSpTransApi
    ) {
        fetchToken(context) { token ->
            val service = RxSpTransportAuthenticationService()
            token?.let {
                service.authenticateInSpTransApi(
                    it,
                    onError = callbackAuthenticationSpTransApi::onError,
                    onSuccess = callbackAuthenticationSpTransApi::onSuccess
                )
            } ?: run {
                callbackAuthenticationSpTransApi.onErrorFetchToken()
            }
        }
    }
}