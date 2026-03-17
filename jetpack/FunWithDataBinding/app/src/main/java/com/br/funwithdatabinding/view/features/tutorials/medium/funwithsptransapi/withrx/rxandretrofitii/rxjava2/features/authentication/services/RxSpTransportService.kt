package com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava2.features.authentication.services

import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.models.SP_TRANS_OLHO_VIVO_API_BASE_URL
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.service.SpTransportApiInterceptor
import com.br.restclientlib.ProviderEndpointClient.createCompatRxJava2Service
import com.br.restclientlib.utils.compat.rxjava2.reactiveCallService
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * Usando RxJava2
 * https://medium.com/@nishargrocks007/using-retrofit-and-rxjava-in-your-android-project-8225f54df614
 */
interface RxSpTransportAuthenticationApi {
    @POST("Login/Autenticar")
    fun authentication(@Query("token") token: String): Single<Response<Boolean>>
}


class RxSpTransportAuthenticationService {

    fun authenticateInSpTransApi(
        token: String,
        onSubscribe: ((Disposable) -> Unit)? = null,
        onError: (Throwable) -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        val service = createCompatRxJava2Service<RxSpTransportAuthenticationApi>(
            SP_TRANS_OLHO_VIVO_API_BASE_URL,
            createOkHttpClient = { SpTransportApiInterceptor.addInterceptors() }
        )

        reactiveCallService { service.authentication(token) }
            .subscribe(object : SingleObserver<Response<Boolean>> {
                override fun onSubscribe(disposable: Disposable) {
                    onSubscribe?.invoke(disposable)
                }

                override fun onError(e: Throwable) {
                    onError(e)
                }

                override fun onSuccess(response: Response<Boolean>) {
                    response.body()?.let(onSuccess)
                }
            })
    }
}