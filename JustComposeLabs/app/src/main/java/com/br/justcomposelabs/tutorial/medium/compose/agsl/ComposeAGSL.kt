package com.br.justcomposelabs.tutorial.medium.compose.agsl

import android.graphics.RuntimeShader
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.utils.composable.paddingEdgeToEdge


// ...

val shaderSource = """
    half4 main(float2 fragCoord) {
        return half4(1, 0, 0, 1);
    }
""".trimIndent()


val glowButtonShader = """
    uniform shader button;
    uniform float2 size;
    uniform float cornerRadius;
    uniform float glowRadius;
    uniform float glowIntensity;
    uniform layout(color) half4 glowColor;

    float calculateRoundedRectSDF(vec2 position, vec2 rectSize, float radius) {
        vec2 adjustedPosition = abs(position) - rectSize + radius;
        return min(max(adjustedPosition.x, adjustedPosition.y), 0.0) + length(max(adjustedPosition, 0.0)) - radius;
    }

    half4 main(float2 fragCoord) {
        // ... logic to calculate glow effect ...
        // The main function would use the SDF to determine the glow based on uniforms
        // and then mix it with the base button color or texture.
    }
""".trimIndent()

val checkerboardShader = """
    uniform half4 color1;
    uniform half4 color2;
    
    // Function to create a checkerboard pattern
    half4 checkerBoard(vec2 fragCoord) {
        float size = 100.0;
        float pattern = mod(floor(fragCoord.x / size) + floor(fragCoord.y / size), 2.0);
        return mix(color1, color2, pattern);
    }

    half4 main(float2 fragCoord) {
        return checkerBoard(fragCoord);
    }
""".trimIndent()

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AGSLExample() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13+
        val runtimeShader = remember { RuntimeShader(shaderSource) }
        val brush = remember { ShaderBrush(runtimeShader) }

        Box(
            modifier = Modifier
                .paddingEdgeToEdge()
                .fillMaxSize()
                .background(brush) // Aplica o shader como background
        ) {
            Text("Content")
        }
    } else {
        // Implementação alternativa para versões anteriores do Android
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        )
    }
}