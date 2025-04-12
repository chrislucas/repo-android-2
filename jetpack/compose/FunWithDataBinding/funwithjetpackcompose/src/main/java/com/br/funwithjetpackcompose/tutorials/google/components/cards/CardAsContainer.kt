package com.br.funwithjetpackcompose.tutorials.google.components.cards

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
    https://developer.android.com/develop/ui/compose/quick-guides/content/create-card-as-container
 */


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CardMinimal(modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.run {
            cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        },
        modifier = modifier.size(width = 240.dp, height = 100.dp)
    ) {
        Text(text = "Hello, world")
    }
}