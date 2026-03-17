package com.br.funwithjetpackcompose.tutorials.medium.testing.testinginjetpackcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

sealed class RallyScreen(
    val icon: ImageVector,
    private val body: @Composable ((RallyScreen) -> Unit) -> Unit
) {
    @Composable
    fun Content(onScreenChange: (RallyScreen) -> Unit) {
        body(onScreenChange)
    }
}

class OverviewScreen : RallyScreen(
    icon = Icons.Filled.PieChart,
    body = { }
)

class AccountsScreen : RallyScreen(icon = Icons.Filled.AttachMoney, body = { })
class Bills : RallyScreen(icon = Icons.Filled.MoneyOff, body = { })