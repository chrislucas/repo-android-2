package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.RallyScreen
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme


@Composable
fun OverviewBody(onScreenChange: (RallyScreen) -> Unit = {}) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AlertCard()
        Spacer(Modifier.height(RallyDefaultPadding))
        AccountsCard(onScreenChange)
        Spacer(Modifier.height(RallyDefaultPadding))
        BillsCard(onScreenChange)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OverviewBodyPreview() {
    JustComposeLabsTheme {
        Box(
            modifier = Modifier
                .systemBarsPadding()
                .statusBarsPadding()
                .fillMaxSize()
        ) {
            OverviewBody(onScreenChange = {})
        }
    }
}
