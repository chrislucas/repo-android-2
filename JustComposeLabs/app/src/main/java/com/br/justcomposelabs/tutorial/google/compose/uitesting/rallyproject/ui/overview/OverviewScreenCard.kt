package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.tutorial.google.codelabs.jetpackcomposetesting.ending.data.UserData
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components.AccountRow
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components.formatAmount
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

private const val SHOWN_ITEMS = 3

internal val RallyDefaultPadding = 12.dp

/**
 * Base structure for cards in the Overview screen.
 */
@Composable
fun <T> OverviewScreenCard(
    title: String,
    amount: Float,
    onClickSeeAll: () -> Unit,
    values: (T) -> Float,
    colors: (T) -> Color,
    data: List<T>,
    row: @Composable (T) -> Unit
) {
    Card {
        Column {
            Column(Modifier.padding(RallyDefaultPadding)) {
                Text(text = title, style = MaterialTheme.typography.titleSmall)
                val amountText = "$${formatAmount(amount)}"
                Text(text = amountText, style = MaterialTheme.typography.titleMedium)
            }
            OverViewDivider(data, values, colors)
            Column(Modifier.padding(start = 16.dp, top = 4.dp, end = 8.dp)) {
                data.take(SHOWN_ITEMS).forEach { row(it) }
                SeeAllButton(onClick = onClickSeeAll)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OverviewScreenCardPreview() {
    JustComposeLabsTheme {
        Box(
            modifier = Modifier
                .systemBarsPadding()
                .statusBarsPadding()
                .fillMaxSize()
        ) {
            val amount = UserData.accounts.sumOf { it.balance.toDouble() }.toFloat()
            OverviewScreenCard(
                title = "Accounts",
                amount = amount,
                onClickSeeAll = {},
                values = { it.balance },
                colors = { it.color },
                data = UserData.accounts,
                row = { account ->
                    AccountRow(
                        name = account.name,
                        number = account.number,
                        amount = account.balance,
                        color = account.color
                    )
                }
            )
        }
    }
}
