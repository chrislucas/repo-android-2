package com.br.justcomposelabs.tutorial.google.unittest.clickablecomponent

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ClickableTextExample(onClick: () -> Unit) {
    Text(
        text = "Click me!",
        modifier = Modifier.clickable(onClick = onClick)
    )
}