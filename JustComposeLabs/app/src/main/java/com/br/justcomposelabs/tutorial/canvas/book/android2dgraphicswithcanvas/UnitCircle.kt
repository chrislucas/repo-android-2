package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.canvasviews.tutorial.google.usingcomposeinview.InteractiveUnitCircle
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme
import com.br.justcomposelabs.utils.composable.fillMaxSizePadding

@Preview(showBackground = true)
@Composable
fun UnitCircleComponentPreview() {
    JustComposeLabsTheme {
        InteractiveUnitCircle(Modifier.fillMaxSizePadding())
    }
}
