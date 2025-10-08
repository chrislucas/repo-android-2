package com.br.justcomposelabs.tutorial.medium.autoresizetext

import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

/*
    TODO estudar
 */
@Preview(showBackground = true)
@Composable
fun AutoResizeText() {
    JustComposeLabsTheme {
        BasicText(
            "text",
            style = TextStyle(
                fontSize = 12.sp
            ),
            autoSize = TextAutoSize.StepBased(
                minFontSize = 12.sp,
                maxFontSize = 24.sp,
                stepSize = 1.sp
            ),
            maxLines = 1
        )
    }
}