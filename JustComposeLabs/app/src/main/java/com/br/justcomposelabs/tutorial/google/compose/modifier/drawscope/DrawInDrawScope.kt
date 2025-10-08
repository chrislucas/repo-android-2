package com.br.justcomposelabs.tutorial.google.compose.modifier.drawscope

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp




@Preview(showBackground = true)
@Composable
fun DrawCanvas() {
    /*
        https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/drawscope/DrawScope
     */

    Canvas(Modifier.size(120.dp)) { }

}


@Preview(showBackground = true)
@Composable
fun DrawScopeRetargetingSample() {
    /*
        https://composables.com/docs/androidx.compose.ui/ui-graphics/functions/draw
     */
}