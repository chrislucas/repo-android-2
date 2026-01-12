package com.br.debuggingcompose.tutorial.google.custommodifier.composedfunction

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo

/*
    https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier#(androidx.compose.ui.Modifier).composed(kotlin.Function1,kotlin.Function1)
 */

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.customColorModifier(color: Color) =
    composed(
        // pass inspector information for debug
        inspectorInfo =
            debugInspectorInfo {
                // name should match the name of the modifier
                name = "myColorModifier"
                // specify a single argument as the value when the argument name is irrelevant
                value = color
            },
        // pass your modifier implementation that resolved per modified element
        factory = {
            // add your modifier implementation here
            Modifier
        },
    )