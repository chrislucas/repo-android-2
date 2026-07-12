package com.br.justcomposelabs.tutorial

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.br.justcomposelabs.tutorial.medium.multiplespreviews.MultiDeviceOrientationPreviews
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

@Composable
fun CenteredBoxSlotComponent(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@MultiDeviceOrientationPreviews
@Composable
fun CenteredBoxSlotComponentPreview() {
    JustComposeLabsTheme {
        /*
            https://share.google/aimode/Kibp2LcVGGXuBNKXg
         */
        CenteredBoxSlotComponent(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .systemBarsPadding()
        ) {
            Text(
                text = "Top Start",
                modifier = Modifier.align(Alignment.TopStart)
            )

            Text(
                text = "Top Center",
                modifier = Modifier.align(Alignment.TopCenter)
            )

            Text(
                text = "Top End",
                modifier = Modifier.align(Alignment.TopEnd)
            )

            Text(
                text = "Center Start",
                modifier = Modifier.align(Alignment.CenterStart)
            )

            Text(
                text = "Center",
                modifier = Modifier.align(Alignment.Center)
            )

            Text(
                text = "Center End",
                modifier = Modifier.align(Alignment.CenterEnd)
            )

            Text(
                text = "Bottom Start",
                modifier = Modifier.align(Alignment.BottomStart)
            )

            Text(
                text = "Bottom Center",
                modifier = Modifier.align(Alignment.BottomCenter)
            )

            Text(
                text = "Bottom End",
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}
