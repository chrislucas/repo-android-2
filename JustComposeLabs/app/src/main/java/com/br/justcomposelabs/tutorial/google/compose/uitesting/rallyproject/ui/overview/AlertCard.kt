package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.overview

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components.ButtonConfirmContext
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components.ButtonDismissContext
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components.RallyAlertDialog
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components.RallyAlertDialogContext
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components.RallyDivider
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme
import java.util.Locale

@Composable
fun AlertCard() {
    var showDialog by remember { mutableStateOf(false) }
    val alertMessage = "Heads up, you've used up 90% of your Shopping budget for this month."
    if (showDialog) {
        RallyAlertDialog(
            rallyAlertDialogContext = RallyAlertDialogContext(
                buttonConfirmContext = ButtonConfirmContext(
                    onConfirm = { showDialog = false },
                    confirmButtonText = "Confirm".uppercase(Locale.getDefault())
                ),
                buttonDismissContext = ButtonDismissContext(
                    onDismiss = { showDialog = false },
                    dismissButtonText = "Dismiss".uppercase(Locale.getDefault())
                ),
                bodyText = alertMessage,
                onTapOutside = {}
            )
        )
    }

    /*
        // Comentado esse bloco daqui
        var currentTargetElevation by remember { mutableStateOf(1.dp) }
        LaunchedEffect(Unit) {
            // Start the animation
            currentTargetElevation = 8.dp
        }
        val animatedElevation = animateDpAsState(
            targetValue = currentTargetElevation,
            animationSpec = tween(durationMillis = 500),
            finishedListener = {
                currentTargetElevation = if (currentTargetElevation > 4.dp) {
                    1.dp
                } else {
                    8.dp
                }
            }
        )
        // estudar o que ele faz
        // até aqui

     */

    val infiniteElevationAnimation = rememberInfiniteTransition()
    val animatedElevation: Dp by infiniteElevationAnimation.animateValue(
        initialValue = 1.dp,
        targetValue = 8.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(500),
            repeatMode = RepeatMode.Reverse
        )
    )

    /**
     * @see androidx.compose.material.Card
     *
     * Existem duas versoes de Card, uma que recebe uma lambda onClick: () ->
     * e outra que não
     */
    Card(elevation = CardDefaults.cardElevation(defaultElevation = animatedElevation)) {
        Column {
            AlertHeader {
                showDialog = true
            }
            RallyDivider(
                modifier = Modifier.Companion.padding(
                    start = RallyDefaultPadding,
                    end = RallyDefaultPadding
                )
            )
            AlertItem(alertMessage)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlertCardPreview() {
    JustComposeLabsTheme {
        Box(
            modifier = Modifier
                .systemBarsPadding()
                .statusBarsPadding()
                .fillMaxSize()
        ) {
            OverviewBody()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlertCardOnlyPreview() {
    JustComposeLabsTheme {
        Box(
            modifier = Modifier
                .systemBarsPadding()
                .statusBarsPadding()
                .fillMaxSize()
        ) {
            AlertCard()
        }
    }
}
