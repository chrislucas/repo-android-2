package com.br.features.tutorials.google.testingcoroutines

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FakeRepositoryTest {


    @Test
    fun testFetchString() = runTest {
        val repository = FakeRepository()
        assertEquals("Hello", repository.fetch())
    }

}
