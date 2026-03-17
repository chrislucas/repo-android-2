package com.br.funwithjetpackcompose.tutorials.google.textstyle

import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import org.junit.Rule
import org.junit.Test


class BrushUsingSpanStyleKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testBrushSpanStyleComposable() {
        // Configura o ambiente de teste com a função composable
        composeTestRule.setContent {
            BrushSpanStyle()
        }

        // Verifica se o texto principal está sendo exibido
        composeTestRule.onNodeWithText("Do not allow people to dim your shine")
            .assertIsDisplayed()

        // Verifica se o texto estilizado está sendo exibido
        composeTestRule.onNodeWithText("because they are blinded.")
            .assertIsDisplayed()

        // Verifica se o texto final está sendo exibido
        composeTestRule.onNodeWithText("Tell them to put some sunglasses on.")
            .assertIsDisplayed()
    }

    @Test
    fun testBrushSpanStyleWithDifferentText() {
        composeTestRule.setContent {
            Text(
                text = buildAnnotatedString {
                    append("Texto inicial\n")
                    withStyle(
                        SpanStyle(
                            brush = Brush.linearGradient(
                                colors = listOf(Yellow, Red, Blue)
                            )
                        )
                    ) {
                        append("Texto estilizado.")
                    }
                    append("\nTexto final.")
                }
            )
        }

        composeTestRule.onNodeWithText("Texto inicial")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Texto estilizado.")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Texto final.")
            .assertIsDisplayed()
    }

    @Test
    fun testBrushSpanStyleWithDifferentBrush() {
        composeTestRule.setContent {
            Text(
                text = buildAnnotatedString {
                    append("Texto inicial\n")
                    withStyle(
                        SpanStyle(
                            brush = Brush.linearGradient(
                                colors = listOf(Blue, Yellow, Red)
                            )
                        )
                    ) {
                        append("Texto estilizado com outro gradiente.")
                    }
                    append("\nTexto final.")
                }
            )
        }

        composeTestRule.onNodeWithText("Texto estilizado com outro gradiente.")
            .assertIsDisplayed()
    }
}