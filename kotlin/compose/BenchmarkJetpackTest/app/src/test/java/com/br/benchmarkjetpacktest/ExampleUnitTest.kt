package com.br.benchmarkjetpacktest

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

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
}