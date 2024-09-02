package com.br.funwithdatabinding.view.features.tutorials.medium.rxandretrofitii.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.funwithdatabinding.view.features.tutorials.medium.rxandretrofitii.services.oldapi.RxSpTransportService
import io.reactivex.rxjava3.disposables.Disposable

class RxSpTransViewModel: ViewModel() {

    var fetchDataDisposable: Disposable? = null

    val observerIsAuthenticated = MutableLiveData<Boolean>()

    private val isAuthenticated: LiveData<Boolean> =
        observerIsAuthenticated


    fun checkAuthentication(token: String) {
        val service = RxSpTransportService()
        service.authenticateInSpTransApi(token, { error ->
            observerIsAuthenticated.value = false
        }) { isSucceedAuthentication ->
            observerIsAuthenticated.value = isSucceedAuthentication
        }
    }
}