package com.br.justcomposelabs.tutorial.google.quickguides.insets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun BoxInsets() {
    Box(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {

    }
}