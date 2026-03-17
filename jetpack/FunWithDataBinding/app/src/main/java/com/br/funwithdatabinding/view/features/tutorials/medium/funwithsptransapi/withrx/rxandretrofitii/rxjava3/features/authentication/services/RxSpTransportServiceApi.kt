package com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava3.features.authentication.services

import com.br.restclientlib.ProviderEndpointClient.createService
import com.br.restclientlib.utils.compat.rxjava3.reactiveCallService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Usando RxJava3
 * https://medium.com/@nishargrocks007/using-retrofit-and-rxjava-in-your-android-project-8225f54df614
 */

interface RxSpTransportApi {

    @POST("Login/Autenticar")
    fun authentication(@Query("token") token: String): Single<Response<Boolean>>
}


class RxSpTransportService {

    companion object {
        private const val BASE_URL = "http://api.olhovivo.sptrans.com.br/v2.1/"
    }

    private val service = createService<RxSpTransportApi>(BASE_URL)

    fun authenticateInSpTransApi(
        token: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {

        reactiveCallService { service.authentication(token) }
            .subscribe(object : SingleObserver<Response<Boolean>> {
                override fun onSubscribe(disposable: Disposable) {}

                override fun onError(e: Throwable) {
                    onError(e)
                }

                override fun onSuccess(response: Response<Boolean>) {
                    response.body()?.let(onSuccess)
                }
            })
    }
}