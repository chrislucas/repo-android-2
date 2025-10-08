package com.br.justcomposelabs.tutorial.composable.surface

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

/*
    Handling user interactions
    https://developer.android.com/develop/ui/compose/touch-input/user-interactions/handling-interactions

    - A documentacao de gestos em compuse cpbre como components compose manipulam
    num baixo nivel os pointer-events, tais como pointer moves e clicks
        - https://developer.android.com/develop/ui/compose/touch-input/pointer-input/understand-gestures
    - Compose abstrai tais low level events em high level interactions
    - Compreender tais higher-leve abstraction podem ajudar em personalizar com
    a UI responde ao usuario
        - Exemplo: Modificar como a aparencia de componente muda quando o usuario interage
        com ele, ou como manter um log dessa interacao.
        - A documentacao nos da informacoes de como modificar o elemento padrao para
        projetar um componente personalizado

   - Interactions
    - https://developer.android.com/develop/ui/compose/touch-input/user-interactions/handling-interactions#interactions
    - Em geral nao necessitamos saber como o componente compose interpreta a interacao do usuario
    com ele. O componente button por exemplo depende do Modifier.clickable para
    descobrir se o ususario clicou nele.
        - Se estivermos usando um botao comum, basta implementar o metodo onClock e o
        Modifier.clickable execuca o codigo quando necessariol

        - Entretanto, se quisermos personalizar a resposta de nosso componente UI para uma interacao
        do usuario, necessitamos saber mais sobre como esse interpretador funciona.

    -
 */


@Preview
@Composable
private fun InteractionStateButton() {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(onClick = {}, interactionSource = interactionSource) {
        Text(if (isPressed) "Pressed" else "Not pressed")
    }
}