package com.br.funwithjetpackcompose.tutorials.medium.testing.testinglayout

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test


class TestingLayoutActivityTest {

    /*
        https://developer.android.com/develop/ui/compose/testing
     */

    @get:Rule val composeTestRule = createComposeRule()


    @Test
    fun should_show_greeting_tex() {
        composeTestRule.setContent {
            GreetingText("Chris")
        }

        composeTestRule.onNodeWithText("Hello Chris!").isDisplayed()
    }
}