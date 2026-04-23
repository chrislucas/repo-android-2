package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.br.canvasviews.Cartesian3DView
import com.br.justcomposelabs.tutorial.google.usingviewsincompose.ui.theme.JustComposeLabsTheme
import com.br.justcomposelabs.utils.composable.fillMaxSizePadding

@Composable
fun DrawCartesian3D(modifier: Modifier = Modifier) {
    AndroidView(modifier = modifier, factory = { context ->
        Cartesian3DView(context)
    }, update = {
        /*
            Aqui a view esta sendo inflatada
         */

        it.invalidate()
    })
}

@Preview(showBackground = true)
@Composable
fun DrawCartesian3DPreview() {
    JustComposeLabsTheme {
        DrawCartesian3D(modifier = Modifier.fillMaxSizePadding())
    }
}
