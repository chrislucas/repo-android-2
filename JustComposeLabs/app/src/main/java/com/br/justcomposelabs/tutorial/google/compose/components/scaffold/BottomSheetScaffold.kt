package com.br.justcomposelabs.tutorial.google.compose.components.scaffold

import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color

/*
    https://gist.github.com/jershell/5630d4860724d1a21a0ec72452e58d16
 */

@Composable
fun BottomSheetScaffoldComponent() {
    val colors =
        remember {
            listOf<Color>(
                Color.Blue,
                Color.Gray,
                Color.Green,
                Color.Magenta,
                Color.Yellow,
                Color.Cyan,
            )
        }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(sheetContent = {}, scaffoldState = scaffoldState) { }
}
