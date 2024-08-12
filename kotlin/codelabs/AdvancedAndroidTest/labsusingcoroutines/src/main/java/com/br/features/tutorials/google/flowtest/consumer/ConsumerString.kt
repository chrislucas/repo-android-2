package com.br.features.tutorials.google.flowtest.consumer

import com.br.features.tutorials.google.flowtest.producer.ProducerString

class ConsumerString(private val producerString: ProducerString) {



    /**
     * Fonte
     * https://developer.android.com/kotlin/flow/test#producer
     *
     * Quando o componente que sera testado é um consumidor de um flow, uma forma comum de
     * testa-lo é trocar o "Produtor" por uma implementacao fake (mock)
     *
     * - O exemplo do link mostra um diagrama onde um ConsumerString consome dados de um Repository cuja
     * fonte de dados são 2 DataSource. Os dados são fornecidos desde o DataSource via Flow
     */

    suspend fun consumer(callback: (String) -> Unit) {
        producerString.produceString().collect {
            callback(it)
        }
    }
}