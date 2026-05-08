package com.br.justcomposelabs.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GenerateStringTest {

    @Test
    fun `generateRandomString should return string of specified length`() {
        val lengths = listOf(1, 10, 100)
        lengths.forEach { length ->
            val result = generateRandomString(length)
            assertEquals("Length should be $length", length, result.length)
        }
    }

    @Test
    fun `generateRandomString should return only alphanumeric characters`() {
        val result = generateRandomString(100)
        val alphaNumericRegex = Regex("^[a-zA-Z0-9]*$")
        assertTrue("String should only contain alphanumeric characters: $result", result.matches(alphaNumericRegex))
    }

    @Test
    fun `generateRandomString with length 0 or negative should return empty string`() {
        assertEquals("", generateRandomString(0))
        assertEquals("", generateRandomString(-5))
    }

    @Test
    fun `generateRandomStrings should return correct number of strings separated by separator`() {
        val count = 5
        val separator = "::"
        val result = generateRandomStrings(count, separator)
        val splitResult = result.split(separator)
        assertEquals(count, splitResult.size)
        assertTrue(result.contains(separator))
    }

    @Test
    fun `generateRandomStrings with count 1 should not contain separator`() {
        val separator = "###"
        val result = generateRandomStrings(1, separator)
        assertFalse("Result should not contain separator when count is 1", result.contains(separator))
    }

    @Test
    fun `generateRandomStrings should respect the length range for individual strings`() {
        val count = 20
        val minLength = 5
        val maxLength = 15
        val range = minLength..maxLength
        
        val result = generateRandomStrings(count = count, stringLengthRange = range)
        val splitResult = result.split(", ") // Default separator
        
        assertEquals(count, splitResult.size)
        splitResult.forEach { str ->
            assertTrue("String length ${str.length} should be within range $range", str.length in range)
        }
    }

    @Test
    fun `generateRandomStrings should work with a single value range`() {
        val count = 5
        val length = 7
        val range = length..length
        
        val result = generateRandomStrings(count, stringLengthRange = range)
        val splitResult = result.split(", ")
        
        splitResult.forEach { str ->
            assertEquals(length, str.length)
        }
    }

    @Test
    fun `generateRandomStrings with count 0 should return empty string`() {
        val result = generateRandomStrings(0)
        assertEquals("", result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `generateRandomStrings should throw exception for negative count`() {
        generateRandomStrings(-1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `generateRandomStrings should throw exception for invalid string length range`() {
        generateRandomStrings(1, stringLengthRange = 0..5)
    }

    @Test
    fun `generated strings should be different (mostly)`() {
        val result = generateRandomStrings(10, "|", 10..10)
        val splitResult = result.split("|")
        val uniqueResults = splitResult.toSet()
        // With 10 strings of length 10 from 62 chars, collisions are extremely unlikely
        assertTrue("Strings should be unique", uniqueResults.size > 1)
    }
}
