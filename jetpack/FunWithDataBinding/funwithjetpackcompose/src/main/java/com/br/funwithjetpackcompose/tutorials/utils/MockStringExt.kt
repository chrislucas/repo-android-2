package com.br.funwithjetpackcompose.tutorials.utils

import kotlin.random.Random

fun randomSequenceOfUniqueString(length: Int, seed: Long = System.currentTimeMillis()) = sequence {
    Random(seed).run {
        val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        while (true) {
            val randomString = (1..length)
                .map { nextInt(chars.size) }
                .map(chars::get)
                .joinToString("")
            yield(randomString)
        }
    }
}.distinct()


fun createRandomString(length: Int) = randomSequenceOfUniqueString(length).first()