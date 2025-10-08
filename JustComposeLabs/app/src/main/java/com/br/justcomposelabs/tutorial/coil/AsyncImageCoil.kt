package com.br.justcomposelabs.tutorial.coil

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.svg.SvgDecoder
import com.br.justcomposelabs.R
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

/*
    io.coil-kt.coil3:coil-compose:3.3.0

    https://coil-kt.github.io/coil/svgs/
 */

@Preview(showBackground = true)
@Composable
private fun AsyncImageComponent() {

    val url = "https://t.ctcdn.com.br/lvns56iaSMyHvyTur4JeYS_NYeY=/i606944.png"
    JustComposeLabsTheme {

        /*
            https://composables.com/docs/androidx.compose.material/material/components/Surface
         */
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.honeycomb),
                    contentDescription = stringResource(R.string.app_name),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape),
                )
            }

            AsyncImage(
                model = url,
                contentDescription = ""
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AsyncImageSvgComponent() {
    JustComposeLabsTheme {
        val ctx = LocalContext.current
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)) {

            /*


             */

            val imageLoader = ImageLoader.Builder(ctx)
                .components {
                    add(SvgDecoder.Factory())
                }.build()

            /*
                https://coil-kt.github.io/coil/svgs/
             */

            AsyncImage(
                model = "https://coil-kt.github.io/coil/images/coil_logo_black.svg",
                contentDescription = "",
                imageLoader = imageLoader
            )
        }
    }
}


@Preview
@Composable
fun SingletonImageLoader() {
    val s = setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }.build()


    }
}


