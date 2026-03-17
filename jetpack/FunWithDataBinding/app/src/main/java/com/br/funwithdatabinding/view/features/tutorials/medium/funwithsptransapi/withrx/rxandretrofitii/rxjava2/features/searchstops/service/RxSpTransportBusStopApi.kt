package com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava2.features.searchstops.service

import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.models.SpBusLine
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*
    GET /Parada/Buscar?termosBusca={termosBusca}
    https://www.sptrans.com.br/desenvolvedores/api-do-olho-vivo-guia-de-referencia/documentacao-api#docApi-acesso
 */
interface RxSpTransportBusStopApi {
    @GET("Parada/Buscar")
    fun getInfoBusLine(@Query("termosBusca") searchTerm: String): Single<Response<String>>
}


class RxSpTransportBusStopService {

}