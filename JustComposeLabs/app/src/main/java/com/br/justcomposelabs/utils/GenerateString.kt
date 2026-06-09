package com.br.justcomposelabs.utils

import kotlin.random.Random

private val ALPHANUMERIC_CHARS = (('a'..'z') + ('A'..'Z') + ('0'..'9')).toList()
private val ALPHA_CHARS = (('a'..'z') + ('A'..'Z')).toList()

/**
 * Generates a random alphanumeric string of the specified length.
 */
fun generateRandomString(length: Int): String {
    if (length <= 0) return ""
    return (1..length)
        .map { ALPHANUMERIC_CHARS.random() }
        .joinToString(separator = "")
}


fun generateListRandomString(quantity: Int, rangeRandomLength: IntRange = 3 .. 10) = buildList {
    require(quantity >= 0) { "Quantity must be non-negative" }
    require(rangeRandomLength.first >= 3 && rangeRandomLength.last <= 10) {
        "String length must be between 3 and 10"
    }
    repeat(quantity) {
        add(generateRandomString(rangeRandomLength.random()))
    }
}

fun takeRandomString(quantity: Int, rangeRandomLength: IntRange = 3 .. 10) = generateSequence {
    require(quantity >= 0) { "Quantity must be non-negative" }
    require(rangeRandomLength.first >= 3 && rangeRandomLength.last <= 10) {
        "String length must be between 3 and 10"
    }
    buildString {
        repeat(Random.nextInt(rangeRandomLength.first, rangeRandomLength.last)) {
            append(ALPHA_CHARS.random())
        }
    }
}.take(quantity).toList()


/**
 * Generates a specified count of random strings, joined by a separator.
 * Each string's length is randomly chosen within the provided range.
 */
fun generateRandomStrings(
    count: Int,
    separator: String = ", ",
    stringLengthRange: IntRange = 3..10
): String {
    require(count >= 0) { "Count must be non-negative" }
    require(stringLengthRange.first >= 1) {
        "Minimum string length must be at least 1"
    }
    
    return buildList {
        repeat(count) {
            add(generateRandomString(stringLengthRange.random()))
        }
    }.joinToString(separator = separator)
}
