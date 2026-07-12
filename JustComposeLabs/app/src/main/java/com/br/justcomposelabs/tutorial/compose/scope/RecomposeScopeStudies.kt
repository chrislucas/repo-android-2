package com.br.justcomposelabs.tutorial.compose.scope

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme
import com.br.justcomposelabs.utils.composable.fillMaxSizePadding
import timber.log.Timber

/*
    interface RecomposeScope
    - https://developer.android.com/reference/kotlin/androidx/compose/runtime/RecomposeScope
    - Representa um escopo recomposable ou secção de hierarquia de recomposição. Pode ser usado
    para manualmente invalidar o escopo e agendá-lo para recomposição

    - A documentação da interface MutableState também cita RecomposeScope e vale a pena
    ler para entender um pouco mais desse Escopo
        - https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState

        - Definição:
            - Um objeto/interface Mutável onde a propriedade value é lida durante
            a execucao de uma funcao Composable. O RecomposeScope será inscrito para
            receber notificacoes quando o valor da propriedade value mudar. Quando a propriedade
            value é escrita ou alterada, o mecanismo de recomposicao de qualquer inscrito
            no RecomposeScope será agendado.
            Se o valor inscrito for o mesmo do valor atual, nenuma recomposicao ocorre


    Thinking in Compose

    - Recomposition skips as much as possible
        - https://developer.android.com/develop/ui/compose/mental-model#skips
 */

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    /*
        RecomposeScope, na prática: https://share.google/aimode/Fhz2QP0n0FBKzH5cO
        - Escopo Isolado
        - Recomposicao Direcionada
        - Skipping

        - Funcoes como Column e Row são inline, portanto elas não criam um RecomposeScope. Se lermos um
        estado dentro duma Column, o escopo invalidado será da função pai
     */

    var counter by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSizePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Counter: $counter")

        ElevatedButton(onClick = { counter++ }) {
            Text("+")
        }

        // outra funcao composable que cria outro escopo
        TextComponent("Hello World !!!")
    }
}

@Composable
fun TextComponent(message: String) {
    Timber.tag("TEXT_COMPONENT").d(message)
    Text(message)
}

@Composable
fun OptimizeMainScreenComponent(provide: () -> Int) {
    /*
        adiar a leitura de um estado ao usar lambdas para otimizar ainda mais esses escopos
        - https://share.google/aimode/MDSFf9K7lWbVjWigD
            - Adiar a leitura dum estado usando lambdas é uma técnica poderosa de reduzir carga de trabalho
            do RecomposeScope

            - Quando se le um estado diretamente,
             o escopo atual é invalidado, quando se passa uma lambda, você só lê o valor no momento da
             sua execucao, o que pode acontecer numa fase posterior (Layout, Draw), passando a de
             Composicao


     */
    Box(Modifier.offset { IntOffset(x = 0, y = provide()) }) {
        Text("Sou muito mais eficiente!")
    }
}

@Preview(showBackground = true)
@Composable
fun OptimizeMainScreenComponentPreview() {
    JustComposeLabsTheme {
        OptimizeMainScreenComponent {
            67
        }
    }
}

/*
    https://medium.com/@riz_maulana/how-does-compose-determine-which-block-of-code-to-recompose-3bf6bd4dad1e
 */
