package com.br.justcomposelabs.utils

private val ALPHANUMERIC_CHARS = (('a'..'z') + ('A'..'Z') + ('0'..'9')).toList()

/**
 * Generates a random alphanumeric string of the specified length.
 */
fun generateRandomString(length: Int): String {
    if (length <= 0) return ""
    return (1..length)
        .map { ALPHANUMERIC_CHARS.random() }
        .joinToString(separator = "")
}

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
