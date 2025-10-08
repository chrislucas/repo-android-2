package com.br.justcomposelabs.tutorial.google.unittest.simpletexttest

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun MyLayoutWithText(first: String, second: String) {
    Column {
        Text(text = first)
        Text(text = second)
    }
}

@Preview(showBackground = true)
@Composable
private fun MyLayoutWithTextPreview() {
    MaterialTheme {
        MyLayoutWithText(first = "Hello", second = "World")
    }
}