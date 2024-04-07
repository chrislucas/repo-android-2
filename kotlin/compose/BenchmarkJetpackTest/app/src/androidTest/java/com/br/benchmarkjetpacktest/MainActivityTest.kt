package com.br.benchmarkjetpacktest

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

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