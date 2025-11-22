package com.br.justcomposelabs.tutorial.google.viewmodel.viewmodelsandstates

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.R

/*
    https://developer.android.com/codelabs/basic-android-kotlin-compose-viewmodel-and-state#2
 */

@Preview(showBackground = true)
@Composable
fun GameStatus(modifier: Modifier = Modifier, score: Int = 0) {
    Card(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.score, score),
            style = typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )
    }
}