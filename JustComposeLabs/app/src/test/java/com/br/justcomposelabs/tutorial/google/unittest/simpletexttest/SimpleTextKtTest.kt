package com.br.justcomposelabs.tutorial.google.unittest.simpletexttest

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/*
    compose test FINGERPRINT must not be null

     The error "FINGERPRINT must not be null" when running Compose tests,
     particularly in a multiplatform context, indicates that an Android-specific environment variable,

     android.os.Build.FINGERPRINT, is being accessed in a test environment where it is not properly initialized.
     This often happens when instrumented Android tests are mistakenly run on a
     JVM (Java Virtual Machine) instead of an Android emulator or device.


     Explanation:
        @RunWith(RobolectricTestRunner::class):
        This annotation is crucial. It instructs JUnit to run the test using Robolectric,
        a framework that allows Android tests to be run directly on a JVM without requiring an emulator or device.

        Robolectric provides a simulated Android environment, including a non-null
        android.os.Build.FINGERPRINT, which prevents the NullPointerException.


        createComposeRule():
        This creates a ComposeTestRule, which is essential for testing Compose UI.
        It provides methods to set content, interact with UI elements, and perform assertions.

        setContent { ... }:
        This block defines the Composable UI that will be tested.
 */

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class SimpleTextKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    //@OptIn(ExperimentalTestApi::class)
    @Test
    fun `MyLayoutWithText displays both strings`() {
        // Verify that both the 'first' and 'second' strings are rendered by their respective Text composables.
        composeTestRule.setContent {
            MyLayoutWithText(first = "Hello", second = "World")
        }
        //composeTestRule.onNodeWithText("Hello").isDisplayed()
        composeTestRule.onNodeWithText("Hello").assertExists()
        //composeTestRule.onNodeWithText("World").isDisplayed()
        composeTestRule.onNodeWithText("World").assertExists()
        composeTestRule.onNodeWithText("Non Existent Text").assertDoesNotExist()
    }

    @Test
    fun `MyLayoutWithText with empty first string`() {
        // Verify that the first Text composable renders an empty string and the second Text composable renders the 'second' string correctly.
        composeTestRule.setContent {
            MyLayoutWithText(first = "", second = "World")
        }
        composeTestRule.onNodeWithText("Hello").isNotDisplayed()
        composeTestRule.onNodeWithText("World").isDisplayed()
    }

    @Test
    fun `MyLayoutWithText with empty second string`() {
        // Verify that the second Text composable renders an empty string and the first Text composable renders the 'first' string correctly.
        // TODO implement test
    }

    @Test
    fun `MyLayoutWithText with both strings empty`() {
        // Verify that both Text composables render empty strings.
        // TODO implement test
    }

    @Test
    fun `MyLayoutWithText with long first string`() {
        // Verify how the layout handles a very long string for 'first'. Check for truncation, wrapping, or other UI behaviors.
        // TODO implement test
    }

    @Test
    fun `MyLayoutWithText with long second string`() {
        // Verify how the layout handles a very long string for 'second'. Check for truncation, wrapping, or other UI behaviors.
        // TODO implement test
    }

    @Test
    fun `MyLayoutWithText with strings containing special characters`() {
        // Verify that strings with special characters (e.g., emojis, symbols, non-ASCII characters) are rendered correctly.
        // TODO implement test
    }

    @Test
    fun `MyLayoutWithText with strings containing numbers`() {
        // Verify that strings consisting of or containing numbers are rendered correctly.
        // TODO implement test
    }

    @Test
    fun `MyLayoutWithText with strings containing leading trailing whitespace`() {
        // Verify that leading and trailing whitespace in 'first' and 'second' strings are handled as expected (e.g., trimmed or preserved based on Text composable behavior).
        // TODO implement test
    }

    @Test
    fun `MyLayoutWithText with strings containing newline characters`() {
        // Verify how newline characters within the 'first' or 'second' strings affect the rendering within the Text composables and the Column layout.
        // TODO implement test
    }

    @Test
    fun `MyLayoutWithText recomposition with changed strings`() {
        // Verify that the UI updates correctly when the 'first' or 'second' string parameters are changed after initial composition.
        // TODO implement test
    }

    @Test
    fun `MyLayoutWithText preview rendering`() {
        // Verify that the @Preview annotated 'MyLayoutWithTextPreview' renders correctly with the default 'Hello' and 'World' strings within a MaterialTheme.
        // TODO implement test
    }

}