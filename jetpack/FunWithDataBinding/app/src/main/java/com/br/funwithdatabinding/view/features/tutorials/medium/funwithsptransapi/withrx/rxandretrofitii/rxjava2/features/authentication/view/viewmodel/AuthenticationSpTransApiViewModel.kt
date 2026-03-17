package com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava2.features.authentication.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava2.features.authentication.services.RxSpTransportAuthenticationService
import io.reactivex.disposables.Disposable

class AuthenticationSpTransApiViewModel : ViewModel() {

    private var fetchDataDisposable: Disposable? = null

    private val observerIsAuthenticated = MutableLiveData<Boolean>()

    val isAuthenticated: LiveData<Boolean>
        get() = observerIsAuthenticated

    /*
        PostValue Vs SetValue
     */
    fun checkAuthentication(token: String) {
        val service = RxSpTransportAuthenticationService()
        service.authenticateInSpTransApi(token, { disposable ->
            fetchDataDisposable = disposable
        }, { _ ->
            observerIsAuthenticated.postValue(false)
        }) { isSucceedAuthentication ->
            observerIsAuthenticated.postValue(isSucceedAuthentication)
        }
    }
}