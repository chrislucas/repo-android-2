package com.br.justcomposelabs.tutorial.google.compose.graphics.modifiers.rotation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.R

/*
    https://developer.android.com/develop/ui/compose/graphics/draw/modifiers#graphics-modifier-rotate
 */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RotatedImageComponent() {
    Box(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(id = R.drawable.cupcake),
            contentDescription = "cupcale",
            modifier = Modifier.graphicsLayer {
                rotationZ = 45f
            }
        )
    }
}
