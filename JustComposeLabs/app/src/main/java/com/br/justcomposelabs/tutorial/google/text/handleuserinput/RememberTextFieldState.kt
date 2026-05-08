package com.br.justcomposelabs.tutorial.google.text.handleuserinput

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.utils.composable.fillMaxSizePadding


@Preview(showBackground = true)
@Composable
fun RememberTextFieldState() {
    val state = rememberTextFieldState()

    Box(
        modifier = Modifier.fillMaxSizePadding(),
        contentAlignment = Alignment.Center
    ) {
        TextField(state, label = {
            Text("enter")
        })
    }
}