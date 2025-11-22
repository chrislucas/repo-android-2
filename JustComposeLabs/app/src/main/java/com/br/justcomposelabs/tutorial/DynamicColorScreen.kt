package com.br.justcomposelabs.tutorial

import android.graphics.BitmapFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.palette.graphics.Palette
import com.br.justcomposelabs.R
import androidx.compose.ui.platform.LocalResources
import com.br.justcomposelabs.utils.pallete.generatePalette


@Composable
fun DynamicColorScreen(
    modifier: Modifier = Modifier,
    palette: Palette?
) {

    palette?.let {
        val backgroundColor = it.vibrantSwatch?.rgb?.let(::Color) ?: Color.Gray
        val textColor = it.vibrantSwatch?.bodyTextColor?.let(::Color) ?: Color.Black

        Column(modifier = modifier.background(backgroundColor)) {
            Text(
                text = "Hello Dynamic Color!",
                color = textColor
            )
        }
    } ?: run {
        Column(modifier = modifier) {
            Text("Loading...")
        }
    }

}

/*
    TODO: Preview nao ta aparecendo
 */

@Preview(showBackground = true)
@Composable
fun DynamicColorScreenPreview() {

    val ctx = LocalResources.current

    val observablePalette = remember { mutableStateOf<Palette?>(null) }

    LaunchedEffect(Unit) {
        observablePalette.value = generatePalette(
            BitmapFactory.decodeResource(ctx, R.drawable.honeycomb)
        )
    }

    observablePalette.value?.let {
        DynamicColorScreen(Modifier.fillMaxSize(), it)
    }
}
