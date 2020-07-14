package com.br.xplorer.sample.samplelibtest

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class NumberTheoryTest {


    @Test
    fun expFunTest() {
        Assert.assertEquals(85427, NumberTheory.exp(3245,2333, 89889))
        Assert.assertEquals(64, NumberTheory.exp(32,89889876, 89))
        Assert.assertEquals(56, NumberTheory.exp(32,89889876, 88))
    }
}