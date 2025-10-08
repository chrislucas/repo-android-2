package com.br.justcomposelabs.tutorial.composable.surface

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.R

/*
    https://composables.com/docs/androidx.compose.material/material/components/Surface

    - "Material surface is the central metaphor in material design"

    Cada surface existe em uma dada elevacao, qie influencia como cada
    pedaço de surface visualmente relacionar-se com outras surfaces and como tal
    surface projeta com sombras
 */


@Preview(showBackground = true)
@Composable
fun TrySurface() {
    Surface(
        // Modifier to be applied to the layout corresponding to the surface
        modifier = Modifier.fillMaxSize(),
        // Defines the surface's shape as well its shadow. A shadow is only displayed if the elevation is greater than zero.
        shape = RectangleShape,
        // The background color. Use Color.Transparent to have no color.
        color = MaterialTheme.colorScheme.surface,

        /*
            The preferred content color provided by this Surface to its children.
            Defaults to either the matching content color for color, or if color is not a color
            from the theme, this will keep the same value set above this Surface.

         */

        contentColor = contentColorFor(Color.LightGray),

        /*
            The size of the shadow below the surface. Note that It will not affect z index of the Surface. If you want to change the drawing order you can use Modifier.zIndex.
         */
        tonalElevation = 4.dp,
        shadowElevation = 4.dp,
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Text(
            "Try Surface.",
            fontSize = TextUnit(32f, TextUnitType.Sp),
            modifier = Modifier.wrapContentHeight(),
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun TryClickableSurface() {

    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        /*
            callback to be called when the surface is clicked
         */
        onClick = {},
        /*
            Controls the enabled state of the surface. When false, this surface will not be clickable
         */
        enabled = true,
        interactionSource = interactionSource
    ) {
        Text(
            "Try Clickable Surface.",
            fontSize = TextUnit(32f, TextUnitType.Sp),
            modifier = Modifier.wrapContentHeight(),
            textAlign = TextAlign.Center
        )
    }
}


