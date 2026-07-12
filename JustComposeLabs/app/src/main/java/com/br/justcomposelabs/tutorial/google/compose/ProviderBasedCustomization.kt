package com.br.justcomposelabs.tutorial.google.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.R

/*
    TODO

    To explicitly test extreme density and scale changes at the component level,
    wrap your UI with a custom CompositionLocalProvider: [1]

     https://medium.com/@hiren6997/why-compose-preview-lies-to-you-and-how-to-fix-it-63f5540e4c4f
 */

@Preview(widthDp = 400)
@Composable
fun CustomDensityPreview() {
    CompositionLocalProvider(
        LocalDensity provides Density(density = 2.5f, fontScale = 1.5f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.honeycomb),
                contentDescription = "Sample Image",
                contentScale = ContentScale.Fit, // Scales uniformly; bounds contain the full image
                modifier = Modifier.size(200.dp) // Defines the container size
            )
        }
    }
}
