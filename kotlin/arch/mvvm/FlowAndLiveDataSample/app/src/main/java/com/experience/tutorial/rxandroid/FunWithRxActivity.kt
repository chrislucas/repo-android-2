package com.experience.tutorial.rxandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.experience.tutorial.R
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer

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
            .subscribe {
                Log.i("TEST_OBS_JUST", "$it")
            }.dispose()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fun_with_rx)


    }
}