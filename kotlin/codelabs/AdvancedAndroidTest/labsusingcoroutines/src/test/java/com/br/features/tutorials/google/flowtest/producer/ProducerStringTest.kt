package com.br.features.tutorials.google.flowtest.producer

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ProducerStringTest {


    @Test
    fun testFirstElement() = runTest {
        val producerString = ProducerString()
        assertEquals("Hello World", producerString.produceString().first())
        assertEquals("Hello World", producerString.produceString().single())
    }


    @Test
    fun testWithSingle() = runTest {
        val producerString = ProducerString()

        val flow = producerString.produceString()
        assertEquals("Hello World", flow.single())
    }

}
