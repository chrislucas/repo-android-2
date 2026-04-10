package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components.formatAmount


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
                val amountText = "$" + formatAmount(
                    amount
                )
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