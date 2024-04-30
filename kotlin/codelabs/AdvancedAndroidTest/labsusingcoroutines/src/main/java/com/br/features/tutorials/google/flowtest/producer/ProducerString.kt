package com.br.features.tutorials.google.flowtest.producer

import kotlinx.coroutines.flow.flow

class ProducerString {

    /**
     * Fonte
     * https://developer.android.com/kotlin/flow/test#producer
     *
     * Asserting flow emissions in a test
     * https://developer.android.com/kotlin/flow/test#assert
     *
     * - Se o objeto de teste é o que expoe o Flow, precisamos fazer asserção no elemento que stream de dados
     *  - No caso abaixo emitimos uma String contantes
     *  - No link acima o exemplo usado é de um Repository que recebe 2 DataSources. Para realizar o teste
     *  poderiamos mockalos com mockk ou criar uma classe Fake que implementa-se o DataSource e ai  instancia-la
     *  e injetar via construtor
     */
    fun produceString() = flow {
        emit("Hello World")
    }
}