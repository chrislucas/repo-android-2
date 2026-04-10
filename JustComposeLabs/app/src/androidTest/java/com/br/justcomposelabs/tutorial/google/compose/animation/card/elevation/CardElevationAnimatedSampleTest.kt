package com.br.justcomposelabs.tutorial.google.compose.animation.card.elevation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

/**
 * Instrumented tests for [CardElevationAnimated].
 *
 * With hoisted state, asserts the toggle contract for `onIsElevatedChange` plus layout and click semantics.
 */
class CardElevationAnimatedSampleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun cardElevationAnimated_displaysCardContentText() {
        composeTestRule.setContent {
            JustComposeLabsTheme {
                CardElevationAnimated(
                    isElevated = false,
                    onIsElevatedChange = {},
                )
            }
        }
        composeTestRule.onNodeWithText("Card content!").assertIsDisplayed()
    }

    @Test
    fun cardElevationAnimated_cardExposesClickSemantics() {
        composeTestRule.setContent {
            JustComposeLabsTheme {
                CardElevationAnimated(
                    isElevated = false,
                    onIsElevatedChange = {},
                )
            }
        }
        composeTestRule.onNodeWithText("Card content!").assertHasClickAction()
    }

    @Test
    fun cardElevationAnimated_whenNotElevated_click_emitsTrue() {
        val received = mutableListOf<Boolean>()
        composeTestRule.setContent {
            JustComposeLabsTheme {
                var isElevated by remember { mutableStateOf(false) }
                CardElevationAnimated(
                    isElevated = isElevated,
                    onIsElevatedChange = { newValue ->
                        received.add(newValue)
                        isElevated = newValue
                    },
                )
            }
        }
        composeTestRule.onNodeWithText("Card content!").performClick()
        composeTestRule.waitForIdle()
        assertEquals(listOf(true), received)
    }

    @Test
    fun cardElevationAnimated_whenElevated_click_emitsFalse() {
        val received = mutableListOf<Boolean>()
        composeTestRule.setContent {
            JustComposeLabsTheme {
                var isElevated by remember { mutableStateOf(true) }
                CardElevationAnimated(
                    isElevated = isElevated,
                    onIsElevatedChange = { newValue ->
                        received.add(newValue)
                        isElevated = newValue
                    },
                )
            }
        }
        composeTestRule.onNodeWithText("Card content!").performClick()
        composeTestRule.waitForIdle()
        assertEquals(listOf(false), received)
    }

    @Test
    fun cardElevationAnimated_twoSequentialClicks_emitTrueThenFalse() {
        val received = mutableListOf<Boolean>()
        composeTestRule.setContent {
            JustComposeLabsTheme {
                var isElevated by remember { mutableStateOf(false) }
                CardElevationAnimated(
                    isElevated = isElevated,
                    onIsElevatedChange = { newValue ->
                        received.add(newValue)
                        isElevated = newValue
                    },
                )
            }
        }
        composeTestRule.onNodeWithText("Card content!").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Card content!").performClick()
        composeTestRule.waitForIdle()
        assertEquals(listOf(true, false), received)
    }

    @Test
    fun GivenCardElevationAnimated_WhenClickTwice_ThenReturnElevatedStateOrigin() {
        val state = mutableStateOf(false)

        composeTestRule.setContent {
            JustComposeLabsTheme {
                var isElevated by remember { mutableStateOf(false) }
                CardElevationAnimated(isElevated = isElevated, onIsElevatedChange = { newState ->
                    state.value = newState
                    isElevated = newState
                })
            }
        }

        composeTestRule.onNodeWithText("Card content!").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Card content!").performClick()
        composeTestRule.waitForIdle()
        assertFalse(state.value)
    }

    @Test
    fun GivenCardElevationAnimated_WhenClickOnce_ThenReturnElevatedStateAsTrue() {
        val state = mutableStateOf(false)
        composeTestRule.setContent {
            JustComposeLabsTheme {
                var isElevated by remember { mutableStateOf(false) }
                CardElevationAnimated(isElevated = isElevated, onIsElevatedChange = { newState ->
                    state.value = newState
                    isElevated = newState
                })
            }
        }
        composeTestRule.onNodeWithText("Card content!").performClick()
        composeTestRule.waitForIdle()
        assertTrue(state.value)
    }
}
