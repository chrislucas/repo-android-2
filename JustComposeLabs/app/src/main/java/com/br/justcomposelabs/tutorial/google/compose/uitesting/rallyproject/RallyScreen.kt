package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.data.UserData
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.account.AccountsBody
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.bill.BillsBody
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.overview.OverviewBody

enum class RallyScreen(
    val icon: ImageVector,
    private val body: @Composable ((RallyScreen) -> Unit) -> Unit
) {
    Overview(
        icon = Icons.Filled.PieChart,
        body = { onScreenChange ->
            OverviewBody(onScreenChange)
        }
    ),

    Accounts(
        icon = Icons.Filled.AttachMoney,
        body = { AccountsBody(UserData.accounts)}
    ),

    Bills(
        icon = Icons.Filled.AttachMoney,
        body = { BillsBody(UserData.bills) }
    );

    @Composable
    fun Content(onScreenChange: (RallyScreen) -> Unit) {
        body(onScreenChange)
    }
}