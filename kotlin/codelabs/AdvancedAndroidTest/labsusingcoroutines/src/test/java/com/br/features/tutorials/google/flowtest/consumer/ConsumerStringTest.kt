package com.br.features.tutorials.google.flowtest.consumer

import com.br.features.tutorials.google.flowtest.producer.ProducerString
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ConsumerStringTest {

    @Test
    fun testConsumerWithRealProducer() = runTest {
        val consumer = ConsumerString(ProducerString())
        consumer.consumer {
            assertEquals("Hello World", it)
        }
    }

    @Test
    fun testConsumerWithMockedProducer() = runTest {
        val producer = mockk<ProducerString>()
        coEvery { producer.producerString() }.coAnswers {
            flow {
                emit("Hello MothaFocka")
            }
        }

        val consumer = ConsumerString(producer)
        consumer.consumer {
            assertEquals("Hello MothaFocka", it)
        }
    }
}