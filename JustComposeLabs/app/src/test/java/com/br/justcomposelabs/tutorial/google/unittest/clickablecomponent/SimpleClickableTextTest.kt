package com.br.justcomposelabs.tutorial.google.unittest.clickablecomponent

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.intArrayOf
import kotlin.test.Test


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class SimpleClickableTextTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun `given a text view when perform click should execute a lambda function`() {
        var clicked = false
        composeTestRule.setContent {
            ClickableTextExample {
                clicked = true
            }
        }
        composeTestRule.onNodeWithText("Click me!").performClick()
        assertTrue(clicked)
    }
}