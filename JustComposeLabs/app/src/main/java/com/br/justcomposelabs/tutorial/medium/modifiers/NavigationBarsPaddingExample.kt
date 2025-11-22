package com.br.justcomposelabs.tutorial.medium.modifiers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.tutorial.google.compose.modifier.custommodifier.customShapedBackground


/*
    https://www.linkedin.com/posts/mohamed-sobhi74_compose-tip-1-linkedin-ugcPost-7389639834299961344-cWdu/?utm_source=share&utm_medium=member_android&rcm=ACoAAAucV48BgdbCBoMmXrArsYNH-OL_jFGhzfk

    - NavigationBarsPadding
        - Um modifier que adiciona padding a um composable tal que
        o conteudo nao se sobrepoe aos componentes de navegacao do sistema
        - navigationBarsPadding() torna a compose UI system-aware, ou
        ciente dos elementos de navegacao do sistema.

        - É possivel combinar com:
            - statusBarsPadding e imePadding
            - A UI vai se ajustar ao system bars e ao keyboard automaticamente

 */

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BottomButton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .imePadding()
            .customShapedBackground(Color(0xFFB8A9DC), RectangleShape)
    ) {
        Button(
            onClick = {},
            modifier = modifier
                .align(Alignment.BottomCenter)
                //.padding(16.dp)
        ) {
            Text("navigationBarsPadding")
        }
    }
}
