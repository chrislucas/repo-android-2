package com.experience.tutorial.rxandroid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.experience.tutorial.R
import io.reactivex.rxjava3.core.Observable
import java.util.Locale

/*
    https://nglauber.medium.com/introdu%C3%A7%C3%A3o-ao-rx-java-com-kotlin-90c58d184c6b

    Elementos fundamentais
    O RX Java tem os seguintes elementos principais:

    Observables
    - "Um observable e o um tipo de emissor que emite um fluxo de dados de forma sequencial.
    Esse fluxo normalmente é consumido por um Observer"

        - "Um observable é similar a um Iterable, a principal diferenca entre eles eh que um provê acesso as
        sincrono ao fluxo de dados e o outro acesso sincrono, respectivamente."

    Observers
    - "Objetos que consomem os dados emitidos por um Observable"
    - "Observers tratam quaisquer erros que venham a ocorrer enquanto o observable esta emitindo dados. Eles
    também sabem quando nao há mais itens a serem consumidos"


    Rx implementa o padrao observer. Quando um Observable emite algo as seguintes acoes sao chamadas no Observer
        - onNext: Chamado para cada item emitido pelo Observable
        - onComplete: Chamado quando t0dos os itens forem emitidos
        - onError: Caso algum erro ocora durante a emissão

    Schedulers
    Operators

 */

class FunWithRxActivity : AppCompatActivity() {

    private fun checkJustFunction() =
        Observable.just(0xff)
            /*
                registrando um observer/consumer/receiver
             */
            .subscribe {
                Log.i("TEST_OBS_JUST", "$it")
            }.dispose()

    private fun checkFromArray() =
        Observable.fromArray("a", "b", "c")
            .subscribe {
                Log.i("TEST_OBS_FROM_ARRAY", it)
            }.dispose()

    private fun checkFromIterable() =
        Observable.fromIterable(listOf("1", "2", "3"))
            .subscribe(
                { Log.i("TEST_OBS_FROM_IT", it) },
                { throwable -> println("Error: $throwable") },
                { println("Complete") }
            )
            .dispose()

    /*
        create
     */

    private fun checkCreateFun(values: List<String>) {
        /*
             Link que explica como lidar com erros emitidos pelo
             Observable
             https://github.com/ReactiveX/RxJava/wiki/Error-Handling
         */
        Observable.create<String> { emitter ->
            values.forEachIndexed { i, str ->
                if (str.isBlank()) {
                    val message = String.format(
                        Locale.getDefault(),
                        "String na posicao %d está vazia", i
                    )
                    // chamada ao onError
                    // chamada ao onError
                    //emitter.onError(Exception(message))
                    emitter.tryOnError(Exception(message))
                }
                emitter.onNext(str)
            }
            emitter.onComplete()
        }.subscribe(
            { str ->
                Log.i("TEST_CREATE_OBS_KOTLIN", str)
            },
            { t ->
                Log.e("ON_ERROR_SUBS_KOTLIN", t.message ?: "ON_ERROR_SUBS_TEST")
            },
            {}
        ).dispose()
    }


    /*
        Error Handling Operators
        https://github.com/ReactiveX/RxJava/wiki/Error-Handling-Operators
     */

    private fun checkObserverOnError() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fun_with_rx)
        //checkFromIterable()

        checkCreateFun(listOf("a", "b", "       ", "", "c"))
    }
}