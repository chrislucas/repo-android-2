package com.br.justcomposelabs.tutorial.google.unittest.simpletexttest

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


class SimpleTextTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @OptIn(ExperimentalTestApi::class)
    @Test
    fun test() {
        // Verify that both the 'first' and 'second' strings are rendered by their respective Text composables.
        composeTestRule.setContent {
            MyLayoutWithText(first = "Hello", second = "World")
        }
        composeTestRule.onNodeWithText("Hello").assertExists()
        composeTestRule.onNodeWithText("World").assertExists()
        composeTestRule.onNodeWithText("Non Existent Text").assertDoesNotExist()
    }

}