package com.br.funwithjetpackcompose.tutorials.google.copysnippetscode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
/*
    TODO criar uma funcao composable como no link abaixo
    https://github.com/android/snippets/blob/94c2b8fbfe08118fb611c14e6f6dcf1267aa5930/compose/snippets/src/main/java/com/example/compose/snippets/text/TextSnippets.kt#L618
 */

@Composable
fun TextSample(samples: Map<String, @Composable () -> Unit>) {
    MaterialTheme {
        Box(
            Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
        ) {

        }
    }
}