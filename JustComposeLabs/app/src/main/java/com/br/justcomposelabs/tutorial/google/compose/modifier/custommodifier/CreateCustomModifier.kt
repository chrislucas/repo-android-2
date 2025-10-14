package com.br.justcomposelabs.tutorial.google.compose.modifier.custommodifier

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

/*
    https://developer.android.com/develop/ui/compose/custom-modifiers

   - modifiers tem multiplas partes
     - Um Modifier Factory
        - Uma extension function em Modifier, que prove uma API para o modifier customizado
        e permite com que Modifiers sejam facilmente encadeados

        - A Modifier factory produces um os elementos de um modifier usados
        pelo Comose para modificar a UI

     - Modifier Element
        - Onde implementamos o comportamento do modifier

    - A forma mais facil de implmentar um Custom Modifier é implmenetar uma Modifier Factory
    que combina outras Factories
        - Para um comportamento customizadmo, devemos implementar Usando a api Modifier.Node, uma
        api Lower level porem prove mais flexibilidade


    https://www.droidcon.com/2024/11/27/custom-modifiers-in-jetpack-compose/

    Pesquisar: DrawModifierNode
    https://proandroiddev.com/custom-modifiers-in-jetpack-compose-a950e6f9eb72
 */


@Composable
fun Modifier.customRoundedBackground(color: Color) =
    padding(16.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(color)


/*
    Create a custom modifier using a composable modifier factory
    - Usar uma funcao composable passando valores para um Modifier existente é chamado de
    composable modifier factory
 */

@Composable
fun Modifier.fade(enable: Boolean): Modifier {
    val alpha by animateFloatAsState(if (enable) 0.5f else 1.0f)
    return this then Modifier.graphicsLayer { this.alpha = alpha }
}


@Preview(showBackground = true)
@Composable
private fun BoxFade() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fade(true)
            .background(Color(255, 209, 128, 255)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Central Text",
            fontSize = TextUnit(24f, TextUnitType.Sp)
        )
    }
}