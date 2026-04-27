package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.R
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

@Composable
fun SeeAllButton(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier =
        Modifier.Companion
            .height(44.dp)
            .fillMaxWidth(),
    ) {
        Text(stringResource(R.string.see_all))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SeeAllButtonPreview() {
    JustComposeLabsTheme {
        Box(
            modifier =
            Modifier
                .systemBarsPadding()
                .statusBarsPadding()
                .fillMaxSize(),
        ) {
            SeeAllButton(onClick = {})
        }
    }
}
