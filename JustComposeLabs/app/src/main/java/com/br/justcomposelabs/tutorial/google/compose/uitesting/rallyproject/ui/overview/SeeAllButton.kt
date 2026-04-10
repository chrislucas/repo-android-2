package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.overview

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.R

@Composable
fun SeeAllButton(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.Companion
            .height(44.dp)
            .fillMaxWidth()
    ) {
        Text(stringResource(R.string.see_all))
    }
}