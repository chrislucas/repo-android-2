package com.br.uistatepatternviewmodel

import kotlin.random.Random


fun getRandomString(length: Int): String {
    val alpha = listOf(('a'..'z'), ('A'..'Z'), ('0' .. '9')).flatten()
    return (1..length)
        .map { Random.nextInt(alpha.size) }
        .map(alpha::get)
        .joinToString(separator = "")
}


fun getRandomPhrase(length: Int, separate: String) : String =
    buildList {
       repeat(length) {
           add(getRandomString(Random.nextInt(3, 10)))
       }
    }.joinToString(separator = separate)


/*
    TODO estudar lite tensorflow para criar um modelo que gera frases aleatórias de tamano X
    para usar de teste
 */