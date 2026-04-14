package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.account

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.R
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.data.Account
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.data.UserData
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components.AccountRow
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components.StatementBody
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.theme.JustComposeLabsTheme

@Composable
fun AccountsBody(accounts: List<Account>) {
    StatementBody(
        items = accounts,
        amounts = { account -> account.balance },
        colors = { account -> account.color },
        amountsTotal = accounts.map { account -> account.balance }.sum(),
        circleLabel = stringResource(R.string.total),
        rows = { account ->
            AccountRow(
                name = account.name,
                number = account.number,
                amount = account.balance,
                color = account.color
            )
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AccountsBodyPreview() {
    JustComposeLabsTheme {
        AccountsBody(accounts = UserData.accounts)
    }
}
