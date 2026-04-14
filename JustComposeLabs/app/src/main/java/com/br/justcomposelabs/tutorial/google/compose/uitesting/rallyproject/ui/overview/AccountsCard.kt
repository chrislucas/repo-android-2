package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.R
import com.br.justcomposelabs.tutorial.google.codelabs.jetpackcomposetesting.ending.data.UserData
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.RallyScreen
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components.AccountRow
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AccountsCardPreview() {
    JustComposeLabsTheme {
        Box(
            modifier = Modifier
                .systemBarsPadding()
                .statusBarsPadding()
                .fillMaxSize()
        ) {
            AccountsCard(onScreenChange = {})
        }
    }
}
