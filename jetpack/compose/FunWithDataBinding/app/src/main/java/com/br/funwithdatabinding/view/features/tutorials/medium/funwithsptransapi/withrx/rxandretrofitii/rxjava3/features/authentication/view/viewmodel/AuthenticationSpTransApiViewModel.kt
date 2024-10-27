package com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava3.features.authentication.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava2.features.authentication.services.RxSpTransportAuthenticationService

class AuthenticationSpTransApiViewModel : ViewModel() {

    /**
        https://medium.com/@nishargrocks007/using-retrofit-and-rxjava-in-your-android-project-8225f54df614
        Essa ViewModel vai seguir a ideia exposta no tutorial, deixar disponivel
        o MutableLiveData atraves de um metodo get e usar uma instancia da interface Observer
     * @see androidx.lifecycle.Observer
     */

    private val observerIsAuthenticated = MutableLiveData<Boolean>()

    fun getObserverIsAuthenticated() = observerIsAuthenticated

    fun checkAuthentication(token: String) {
        val service = RxSpTransportAuthenticationService()
        service.authenticateInSpTransApi(token,
            onError = { _ ->
                observerIsAuthenticated.postValue(false)
            }) { isSucceedAuthentication ->
            observerIsAuthenticated.postValue(isSucceedAuthentication)
        }
    }
}