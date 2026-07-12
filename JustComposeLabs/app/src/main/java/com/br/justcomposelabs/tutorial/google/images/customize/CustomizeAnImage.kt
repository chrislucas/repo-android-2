package com.br.justcomposelabs.tutorial.google.images.customize

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.R
import com.br.justcomposelabs.tutorial.medium.multiplespreviews.MultiplesDensitiesPreviews

/*
    https://developer.android.com/develop/ui/compose/graphics/images/customize
 */

@MultiplesDensitiesPreviews
@Composable
fun FitImageExample() {
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
