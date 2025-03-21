package com.br.funwithjetpackcompose.tutorials.medium.testing.testinglayout.listbuttons

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.br.mylibrary.test.R
import org.junit.Rule
import org.junit.Test

class ListButtonsActivityTest {
    /*
        https://developer.android.com/develop/ui/compose/testing
     */

    @get:Rule val composeTestRule = createComposeRule()


    @get:Rule val androidComposeRule = createAndroidComposeRule<ListButtonsActivity>()

    @Test
    fun testing() {
        composeTestRule.setContent { RowButtonTypes() }
        composeTestRule.onNode(
            hasText("Button") and hasClickAction()
        ).performClick()

        composeTestRule.onNodeWithText("Button").assertIsDisplayed()
    }



    @Test
    fun testingActivity() {
        androidComposeRule.activity.getString(com.br.mylibrary.R.string.title_activity_list_buttons)
    }
}