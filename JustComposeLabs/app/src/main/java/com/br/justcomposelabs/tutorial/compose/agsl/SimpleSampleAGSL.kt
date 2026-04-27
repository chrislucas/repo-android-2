package com.br.justcomposelabs.tutorial.compose.agsl

import android.graphics.RuntimeShader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val AGSL_SHADER =
    """
    uniform float2 uResolution;
    uniform float uTime;
    half4 main(float2 fragCoord) {
        float2 uv = fragCoord / uResolution.xy;
        return half4(uv.x, uv.y, 0.5 + 0.5 * sin(uTime), 1.0);
    }
    """.trimIndent()

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun BoxShaderAGSL() {
    val shader = remember { RuntimeShader(AGSL_SHADER) }
    val brush = ShaderBrush(shader)
    Box(
        modifier =
        Modifier
            .systemBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier =
            Modifier.size(200.dp).drawBehind {
                shader.setFloatUniform("uResolution", size.width, size.height)
                drawRect(brush)
            },
        )
    }
}
