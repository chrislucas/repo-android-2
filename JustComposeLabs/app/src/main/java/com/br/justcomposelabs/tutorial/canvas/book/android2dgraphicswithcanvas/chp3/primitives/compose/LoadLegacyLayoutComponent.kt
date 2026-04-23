package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives.compose

import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.br.justcomposelabs.R
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

@Composable
fun LoadLegacyLayoutComponent(@LayoutRes layoutId: Int, modifier: Modifier) {
    AndroidView(
        factory = { context ->
            /*
                 Use a FrameLayout as a container to support
                 <merge> tags and avoid null return from View.inflate/
             */
            View.inflate(context, layoutId, FrameLayout(context))
        },
        update = {
            it.invalidate()
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun LoadLegacyLayoutComponentPreview() {
    JustComposeLabsTheme {
        LoadLegacyLayoutComponent(
            R.layout.layout_custom_view_overlay,
            Modifier.fillMaxSize()
                .navigationBarsPadding()
                .systemBarsPadding()
        )
    }
}
