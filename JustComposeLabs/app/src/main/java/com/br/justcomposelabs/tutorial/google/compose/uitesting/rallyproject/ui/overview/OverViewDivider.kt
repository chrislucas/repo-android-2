package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.tutorial.google.codelabs.jetpackcomposetesting.ending.data.UserData
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

@Composable
fun <T> OverViewDivider(
    data: List<T>,
    values: (T) -> Float,
    colors: (T) -> Color
) {
    Row(Modifier.fillMaxWidth()) {
        data.forEach { item: T ->
            Spacer(
                modifier = Modifier
                    .weight(values(item))
                    .height(1.dp).background(colors(item))
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OverViewDividerPreview() {
    JustComposeLabsTheme {
        Box(
            modifier = Modifier
                .systemBarsPadding()
                .statusBarsPadding()
                .fillMaxSize()
        ) {
            OverViewDivider(
                data = UserData.accounts,
                values = { it.balance },
                colors = { it.color }
            )
        }
    }
}
