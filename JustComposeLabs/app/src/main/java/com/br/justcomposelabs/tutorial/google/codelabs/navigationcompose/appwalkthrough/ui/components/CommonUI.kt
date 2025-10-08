package com.br.justcomposelabs.tutorial.google.codelabs.navigationcompose.appwalkthrough.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.br.justcomposelabs.R
import com.br.justcomposelabs.utils.composable.ShowBackgroundOrNot

@Composable
fun FormattedPriceLabel(subtotal: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.subtotal_price, subtotal),
        style = MaterialTheme.typography.headlineSmall
    )
}

@ShowBackgroundOrNot
@Composable
private fun FormattedPriceLabelPreview() {
    FormattedPriceLabel(subtotal = "299.99")
}
