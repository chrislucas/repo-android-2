package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.R
import com.br.justcomposelabs.tutorial.google.codelabs.jetpackcomposetesting.ending.data.UserData
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.RallyScreen
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components.AccountRow


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


/**
 * The Accounts card within the Rally Overview screen.
 */
@Composable
internal fun AccountsCard(onScreenChange: (RallyScreen) -> Unit) {
    val amount = UserData.accounts.map { account -> account.balance }.sum()
    OverviewScreenCard(
        title = stringResource(R.string.accounts),
        amount = amount,
        onClickSeeAll = {
            onScreenChange(RallyScreen.Accounts)
        },
        data = UserData.accounts,
        colors = { it.color },
        values = { it.balance }
    ) { account ->
        AccountRow(
            name = account.name,
            number = account.number,
            amount = account.balance,
            color = account.color
        )
    }
}

