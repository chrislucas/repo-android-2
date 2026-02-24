package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.RallyScreen

private val RallyDefaultPadding = 12.dp

private const val SHOWN_ITEMS = 3

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

@Composable
private fun AlertCard() {}


@Composable
private fun AccountsCard(onScreenChange: (RallyScreen) -> Unit) {}


@Composable
private fun BillsCard(onScreenChange: (RallyScreen) -> Unit) {}