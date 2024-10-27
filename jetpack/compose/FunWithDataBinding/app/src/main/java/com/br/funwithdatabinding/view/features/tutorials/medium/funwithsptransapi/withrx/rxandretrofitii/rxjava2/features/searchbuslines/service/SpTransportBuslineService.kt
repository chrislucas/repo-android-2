package com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava2.features.searchbuslines.service

import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.service.CallbackOnHttpRequest
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.models.SP_TRANS_OLHO_VIVO_API_BASE_URL
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.models.SpBusLine
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.service.SpTransportApiInterceptor
import com.br.restclientlib.ProviderEndpointClient.createCompatRxJava2Service
import com.br.restclientlib.utils.compat.rxjava2.reactiveCallService
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RxSpTransportBusLineApi {
    @GET("Linha/Buscar")
    fun getInfoBusLine(
        @Header("Cookie") cookies: Set<String>,
        @Query("termosBusca") searchTerm: String
    ): Single<Response<List<SpBusLine>>>

    @GET("Linha/Buscar")
    fun getInfoBusLineByDirection(
        @Query("termosBusca") searchTerm: String,
        @Query("sentido") direction: String
    ): Single<Response<List<SpBusLine>>>
}


class RxSpTransportBusLineService {

    private val service = createCompatRxJava2Service<RxSpTransportBusLineApi>(
        SP_TRANS_OLHO_VIVO_API_BASE_URL,
        /*
            TODO melhorar isso
         */
        createOkHttpClient = { SpTransportApiInterceptor.addInterceptors() }
    )

    fun getInfoBusLine(
        searchTerm: String,
        callbackOnHttpRequest: CallbackOnHttpRequest<List<SpBusLine>>
    ) {
        reactiveCallService {
            service.getInfoBusLine(SpTransportApiInterceptor.cookies, searchTerm)
        }
            .subscribe(object : SingleObserver<Response<List<SpBusLine>>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) = callbackOnHttpRequest.onException(e)

                override fun onSuccess(response: Response<List<SpBusLine>>) {
                    if (response.isSuccessful) {
                        response.body()?.let(callbackOnHttpRequest::onSuccess)
                    } else {
                        callbackOnHttpRequest.onFail()
                    }
                }
            })
    }
}

