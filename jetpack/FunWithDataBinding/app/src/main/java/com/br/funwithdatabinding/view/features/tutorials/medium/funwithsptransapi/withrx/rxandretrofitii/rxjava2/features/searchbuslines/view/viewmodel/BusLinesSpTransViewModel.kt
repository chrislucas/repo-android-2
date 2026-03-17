package com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava2.features.searchbuslines.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.funwithdatabinding.BuildConfig
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.models.SpBusLine
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.service.CallbackOnHttpRequest
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.service.FirebaseConfigRepository
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava2.features.authentication.services.RxSpTransportAuthenticationService
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava2.features.searchbuslines.service.RxSpTransportBusLineService

class BusLinesSpTransViewModel : ViewModel() {

    private val service = RxSpTransportBusLineService()
    private val serviceAuthentication = RxSpTransportAuthenticationService()

    fun fetchBusLine(token: String, content: String) {
        serviceAuthentication.authenticateInSpTransApi(token,
            onError = {},
            onSuccess = { flag ->
                if (flag) {
                    getInfoBusLine(content)
                }
            })
    }

    private fun getInfoBusLine(content: String) {
        service.getInfoBusLine(content, object : CallbackOnHttpRequest<List<SpBusLine>> {
            override fun onException(exception: Throwable) {
                if (BuildConfig.DEBUG) {
                    Log.i("ERROR", "")
                }
            }

            override fun onFail() {
                // Nothing
            }

            override fun onSuccess(value: List<SpBusLine>) {
                if (BuildConfig.DEBUG) {
                    Log.i("SUCCESS", "$value")
                }
            }
        })
    }

}