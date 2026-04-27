package com.br.justcomposelabs.tutorial.google.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun TextPageContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = lorenIpsum(words = 200),
            modifier = Modifier.fillMaxSize(),
            style =
            TextStyle(
                fontSize = 12.sp,
                textAlign = TextAlign.Justify,
            ),
        )
    }
}

fun lorenIpsum(
    separator: String = " ",
    words: Int,
): String = LoremIpsum(words).values.joinToString(separator)
