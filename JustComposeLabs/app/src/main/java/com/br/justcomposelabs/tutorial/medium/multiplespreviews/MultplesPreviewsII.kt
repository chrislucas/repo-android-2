package com.br.justcomposelabs.tutorial.medium.multiplespreviews

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.R
import com.br.justcomposelabs.utils.composable.paddingEdgeToEdge
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme


@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
annotation class DarkNode

@DevicePreviews
@DarkNode
@Composable
fun GreetingPreviewII() {
    JustComposeLabsTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paddingEdgeToEdge(),

        ) {
            Text(text = stringResource(R.string.greeting))
        }
    }
}