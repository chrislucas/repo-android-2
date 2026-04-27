package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

@Composable
internal fun AlertHeader(onClickSeeAll: () -> Unit) {
    Row(
        modifier =
        Modifier.Companion
            .padding(RallyDefaultPadding)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Alerts",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.Companion.align(Alignment.Companion.CenterVertically),
        )
        TextButton(
            onClick = onClickSeeAll,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.Companion.align(Alignment.Companion.CenterVertically),
        ) {
            Text(
                text = "SEE ALL",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlertHeaderPreview() {
    JustComposeLabsTheme {
        Box(
            modifier =
            Modifier
                .systemBarsPadding()
                .statusBarsPadding()
                .fillMaxSize(),
        ) {
            AlertHeader(onClickSeeAll = {})
        }
    }
}
