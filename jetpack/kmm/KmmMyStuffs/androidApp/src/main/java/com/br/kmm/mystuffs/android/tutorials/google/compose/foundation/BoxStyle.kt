package com.br.kmm.mystuffs.android.tutorials.google.compose.foundation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/*
    https://foso.github.io/Jetpack-Compose-Playground/layout/box/
    https://github.com/Foso/Jetpack-Compose-Playground/blob/master/app/src/main/java/de/jensklingenberg/jetpackcomposeplayground/mysamples/github/layout/BoxExample.kt
    https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/package-summary#Box
 */


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExampleBox(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text("")
    }
}