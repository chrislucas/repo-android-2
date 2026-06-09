package com.br.justcomposelabs.tutorial.google.compose.sideffects.rememberupdatedstate.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme
import java.util.concurrent.atomic.AtomicInteger
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * Instrumented tests for [SplashScreenComponent].
 *
 * Cobre:
 * - Renderização do logo e do texto do nome do app.
 * - Contrato temporal: onTimeout é chamado apenas após a animação (~1000ms) e o delay (2000ms).
 * - Uso correto de rememberUpdatedState: quando a lambda passada muda durante a composição,
 *   apenas a versão mais recente é invocada quando o efeito completar.
 *
 * Para evitar flakiness e tempos longos, o relógio do Compose é controlado via
 * `mainClock.autoAdvance = false` e `advanceTimeBy(...)`.
 */
class SplashScreenComponentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun splashScreen_displaysLogo() {
        composeTestRule.setContent {
            JustComposeLabsTheme {
                SplashScreenComponent(onTimeout = {})
            }
        }
        composeTestRule.onNodeWithContentDescription("Logo").assertIsDisplayed()
    }

    @Test
    fun splashScreen_displaysAppNameText() {
        composeTestRule.setContent {
            JustComposeLabsTheme {
                SplashScreenComponent(onTimeout = {})
            }
        }
        composeTestRule.onNodeWithText("JustComposeLabs").assertIsDisplayed()
    }

    @Test
    fun splashScreen_invokesOnTimeoutAfterAnimationAndDelay() {
        val invocations = AtomicInteger(0)
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.setContent {
            JustComposeLabsTheme {
                SplashScreenComponent(onTimeout = { invocations.incrementAndGet() })
            }
        }
        // Animação (1000ms) + delay (2000ms) + margem.
        composeTestRule.mainClock.advanceTimeBy(3_500L)
        composeTestRule.mainClock.autoAdvance = true
        composeTestRule.waitForIdle()
        assertEquals(1, invocations.get())
    }

    @Test
    fun splashScreen_doesNotInvokeOnTimeoutBeforeDelayCompletes() {
        val invocations = AtomicInteger(0)
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.setContent {
            JustComposeLabsTheme {
                SplashScreenComponent(onTimeout = { invocations.incrementAndGet() })
            }
        }
        // Antes da animação terminar.
        composeTestRule.mainClock.advanceTimeBy(500L)
        assertEquals(0, invocations.get())
    }

    @Test
    fun splashScreen_usesLatestOnTimeoutWhenLambdaChanges() {
        val firstInvocations = AtomicInteger(0)
        val secondInvocations = AtomicInteger(0)
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.setContent {
            JustComposeLabsTheme {
                var useFirst by remember { mutableStateOf(true) }
                val callback: () -> Unit = if (useFirst) {
                    { firstInvocations.incrementAndGet() }
                } else {
                    { secondInvocations.incrementAndGet() }
                }
                SplashScreenComponent(onTimeout = callback)
                // Troca a lambda logo após a primeira composição,
                // antes do efeito completar; rememberUpdatedState garante que
                // a versão mais recente será invocada.
                LaunchedEffect(Unit) {
                    useFirst = false
                }
            }
        }
        composeTestRule.mainClock.advanceTimeBy(3_500L)
        composeTestRule.mainClock.autoAdvance = true
        composeTestRule.waitForIdle()
        assertEquals(0, firstInvocations.get())
        assertEquals(1, secondInvocations.get())
    }
}
