package com.br.justcomposelabs.utils.pallete

import android.graphics.Bitmap
import android.graphics.Color
import androidx.palette.graphics.Palette
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*
    Pesquisar
    Palette API compose

    https://developer.android.com/jetpack/androidx/releases/palette
    https://github.com/nickbutcher/plaid

    How to use Palette API with Jetpack Compose
    https://medium.com/@daniel.atitienei/how-to-use-palette-api-with-jetpack-compose-a7028143d3c2
 */

suspend fun generatePalette(bitmap: Bitmap): Palette {
    return withContext(Dispatchers.IO) {
        Palette.from(bitmap).generate()
    }
}

/*
fun Pallete.getVibrantSwatch(): Int {
    return vibrantSwatch?.rgb ?: Color.Black // Default if not found
}



val vibrantColor = palette.vibrantSwatch?.rgb ?: Color.Black // Default if not found
val onVibrantColor = palette.vibrantSwatch?.bodyTextColor ?: Color.White

 */