package com.br.justcomposelabs.tutorial.composable.modifiers.borders

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
    https://composables.com/docs/androidx.compose.foundation/foundation/modifiers/border
 */

@Preview(showBackground = true)
@Composable
fun BorderSample() {
    Text(
        "Text with  square border",
        modifier = Modifier.border(4.dp, Color.Magenta).padding(10.dp),
    )
}
