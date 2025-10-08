package com.br.justcomposelabs.tutorial.google.unittest.simpletexttest

import android.widget.TextView
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

/*
    Implementar teste para essa classe
 */
@Composable
fun SimpleTextAndroidView(value: String) {
    AndroidView(
        factory = { context ->
            TextView(context).apply {
                text = value
            }
        },
        update = { view ->
            view.text = value
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SimpleTextAndroidViewPreview() {
    MaterialTheme {
        SimpleTextAndroidView(value = "Hello, World!")
    }
}