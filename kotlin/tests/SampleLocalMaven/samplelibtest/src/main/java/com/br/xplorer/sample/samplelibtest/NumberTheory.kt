package com.br.xplorer.sample.samplelibtest

object NumberTheory {

    private fun multiplication(a: Long, b: Long, m: Long) = ((a%m)*(b%m))%m

    @JvmStatic
    fun exp(base: Long, e: Long, m: Long) : Long {
        return when {
            e == 0L -> {
                1L
            }
            e < 0L -> {
                0L
            }
            e == 1L -> {
                base % m
            }
            else -> {
                var acc = 1L
                var cBase = base
                var cE = e

                while(cE > 0) {
                    if (cE and 1 == 1L)
                        acc = multiplication(cBase, acc, m)
                    cBase = multiplication(cBase, cBase, m)
                    cE = cE shr 1
                }
                acc
            }
        }
    }
}