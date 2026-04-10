package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.bill

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.R
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.data.Bill
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.data.UserData
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components.BillRow
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components.StatementBody
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.theme.JustComposeLabsTheme

@Composable
fun BillsBody(bills: List<Bill>) {
    StatementBody(
        items = bills,
        amounts = { bill -> bill.amount },
        colors = { bill -> bill.color },
        amountsTotal = bills.map { bill -> bill.amount }.sum(),
        circleLabel = stringResource(R.string.due),
        rows = { bill ->
            BillRow(bill.name, bill.due, bill.amount, bill.color)
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BillsBodyPreview() {
    JustComposeLabsTheme {
        BillsBody(bills = UserData.bills)
    }
}