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
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components.BillRow
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

/**
 * The Bills card within the Rally Overview screen.
 */
@Composable
fun BillsCard(onScreenChange: (RallyScreen) -> Unit) {
    val amount = UserData.bills.map { bill -> bill.amount }.sum()
    OverviewScreenCard(
        title = stringResource(R.string.bills),
        amount = amount,
        onClickSeeAll = { onScreenChange(RallyScreen.Bills) },
        data = UserData.bills,
        colors = { it.color },
        values = { it.amount },
    ) { bill ->
        BillRow(
            name = bill.name,
            due = bill.due,
            amount = bill.amount,
            color = bill.color,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BillsCardPreview() {
    JustComposeLabsTheme {
        Box(
            modifier =
            Modifier
                .systemBarsPadding()
                .statusBarsPadding()
                .fillMaxSize(),
        ) {
            BillsCard(onScreenChange = {})
        }
    }
}
