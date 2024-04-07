package com.br.checkmicrobenchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {


    @get:Rule
    val benchmarkRule = BenchmarkRule()

    private val collection100 = (1 .. 100).toList()
    private val collection1000 = (1 .. 1000).toList()

    @Test
    fun testCollection100() {
        benchmarkRule.measureRepeated {
            collection100.asSequence()
                .map { it + 1 }
                .map { it + 2 }
                .map { it + 3 }
                .toList()
        }
    }

    @Test
    fun testCollection1000() {
        benchmarkRule.measureRepeated {
            collection1000.asSequence()
                .map { it + 1 }
                .map { it + 2 }
                .map { it + 3 }
                .toList()
        }
    }
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.br.checkmicrobenchmark.test", appContext.packageName)
    }
}