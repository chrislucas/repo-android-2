package com.experience.tutorial.aboutsharedflow.starter.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.experience.tutorial.R

/**
 *  https://www.kodeco.com/22030171-reactive-streams-on-kotlin-sharedflow-and-stateflow
 *  - RxJava era o framework padrao para implementacao de programacao reativa no android,
 *  agora o kotlin tem sua propria implementacao  chamada Flow
 *
 *  - Como Rx, Flow pode criar e reagir a streams de dado
 *  - E assim como Rx o evento pode vir de um cold ou hot publisher
 *      - Cold stream emite eventos somente se ha algum subscriber
 *      - Hot stream pode emitir eventos sem ter qualquer subscriber
 *
 *   - A implementacao de hot stream de Flow é a SharedFlow e StateFlow
 *      - O codigo desse pacote é baseado no link acima
 *      - A ideia é aprender o que é SharedFlow, StateFlow e como ambos se relacionam
 *
 *
 *   O que é o SharedFlow
 *   - Shared Flow é uma implementacao do Flow da biblioteca de coroutines do kotlin.
 *   - Ha duas diferencas da implementacao padrao do Flow
 *      - Ela emite eventos mesmo sem chamar o metodo "collect()" - Hot Stream implementatio
 *      - Pode ter multiplos subscribers
 *
 *  - O termo "subscribers" ao inves de "collector" foi determinado porque SharedFlow nunca completa sua execucao
 *      - Quando chamamos o metodo "Flow.collect", num SharedFlow nao estamos coletando todos os eventos
 *      - Estamos "nos inscrevendo/subscribing" para evetnos que sao emitidos enquando a "inscricao" existir
 *
 *  - Cancelar uma subscription acontece ao cancelar a coroutine
 *  - Operatores de truncamento como Flow.take(count: Int) podem for;car o shared flow a completar a execucao
 */
class CoinCapSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_cap_sample)
    }
}