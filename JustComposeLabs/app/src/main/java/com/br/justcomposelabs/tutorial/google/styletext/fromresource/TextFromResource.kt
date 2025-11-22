package com.br.justcomposelabs.tutorial.google.styletext.fromresource

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.br.justcomposelabs.R


/*
    https://developer.android.com/develop/ui/compose/text/display-text#text-from-resource
 */
@Preview(showBackground = true)
@Composable
fun ResourceText() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            stringResource(R.string.chocolate),
            style = TextStyle(fontSize = 30.sp),
            fontFamily = FontFamily.Cursive
        )
    }
}