package com.br.kmm.multilanguagesupport.tutorials.google.composables.surface

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.w3c.dom.Text

/*
    https://composables.com/material/surface
    - Definido na doccumentacao como o componente central de Material Design
    - Cada Surface existe numa determinada elevacao, que implica como cada pedaço de surface
    se relaciona visualmente com outros e como ela projeta sombra

    -
 */


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SurfaceSample() {
    Surface(color = Color.Gray) {
        Text(text = "Hello World")
    }
}