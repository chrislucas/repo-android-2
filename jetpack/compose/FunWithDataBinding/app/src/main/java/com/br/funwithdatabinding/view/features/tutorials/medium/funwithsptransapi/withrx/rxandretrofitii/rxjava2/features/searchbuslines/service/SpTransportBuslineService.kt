package com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava2.features.searchbuslines.service

import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.service.CallbackOnHttpRequest
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.service.models.SP_TRANS_OLHO_VIVO_API_BASE_URL
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.service.models.SpBusLine
import com.br.restclientlib.ProviderEndpointClient.createCompatRxJava2Service
import com.br.restclientlib.utils.compat.rxjava2.reactiveCallService
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RxSpTransportBusLineApi {
    @GET("/Linha/Buscar")
    fun getInfoBusLine(@Query("termosBusca") searchTerm: String): Single<Response<SpBusLine>>
}


class RxSpTransportBusLineService {

    private val service = createCompatRxJava2Service<RxSpTransportBusLineApi>(
        SP_TRANS_OLHO_VIVO_API_BASE_URL
    )

    fun getInfoBusLine(
        searchTerm: String,
        callbackOnHttpRequest: CallbackOnHttpRequest<SpBusLine>
    ) {
        reactiveCallService { service.getInfoBusLine(searchTerm) }
            .subscribe(object : SingleObserver<Response<SpBusLine>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) = callbackOnHttpRequest.onException(e)

                override fun onSuccess(response: Response<SpBusLine>) {
                    if (response.isSuccessful) {
                        response.body()?.let(callbackOnHttpRequest::onSuccess)
                    } else {
                        callbackOnHttpRequest.onFail()
                    }
                }
            })
    }
}

