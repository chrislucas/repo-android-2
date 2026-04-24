package com.br.funwithcustommodifiers.tutorials.google.graphics.advanced

import org.intellij.lang.annotations.Language

/*
    Advanced example: Custom brush
    https://developer.android.com/develop/ui/compose/graphics/draw/brush#advanced-example
 */

@Language("AGSL")
val CUSTOM_SHADER = """
    uniform float2 resolution;
    layout(color) uniform half4 color;
    layout(color) uniform half4 color2;

    half4 main(in float2 fragCoord) {
        float2 uv = fragCoord/resolution.xy;

        float mixValue = distance(uv, vec2(0, 1));
        return mix(color, color2, mixValue);
    }
""".trimIndent()
