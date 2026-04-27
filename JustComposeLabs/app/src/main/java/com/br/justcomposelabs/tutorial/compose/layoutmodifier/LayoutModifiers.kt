package com.br.justcomposelabs.tutorial.compose.layoutmodifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp

/**
 https://composables.com/community/layout-modifiers
 * @see Modifier.layout
 - interface usada para personalizar medições e posicionamentos
 */

fun Modifier.pad(size: Dp) =
    layout { measurable, constraints ->
        // mede no filho
        val placeable = measurable.measure(constraints)
        // define dimensão, levando em consideração o espaçamento
        val width = placeable.width + size.roundToPx() * 2
        val height = placeable.height + size.roundToPx() * 2
        // posiciona o
        layout(width, height) {
            placeable.place(size.roundToPx(), size.roundToPx())
        }
    }
