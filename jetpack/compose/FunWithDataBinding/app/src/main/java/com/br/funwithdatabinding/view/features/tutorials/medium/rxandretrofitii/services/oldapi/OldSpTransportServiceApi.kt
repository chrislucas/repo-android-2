package com.br.funwithdatabinding.view.features.tutorials.medium.rxandretrofitii.services.oldapi

import com.br.restclientlib.ProviderEndpointClient.createOldReactiveService
import com.br.restclientlib.ProviderEndpointClient.createService
import com.br.restclientlib.utils.SafeServiceCall.reactiveCallService
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
interface SpTransportApi {

    @POST("/Login/Autenticar")
    suspend fun authentication(@Query("token") token: String): Response<Boolean>
}


interface RxSpTransportApi {

    @POST("Login/Autenticar")
    fun authentication(@Query("token") token: String): Single<Response<Boolean>>
}


class RxSpTransportService {

    companion object {
        private const val BASE_URL = "http://api.olhovivo.sptrans.com.br/v2.1/"
    }

    private val service = createOldReactiveService<RxSpTransportApi>(BASE_URL)


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