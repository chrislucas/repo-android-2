package com.br.justcomposelabs.tutorial.composable.functions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

/*
    https://share.google/aimode/Lx4u4qI2FXl5Sy5wT

    fonte
        - https://medium.com/better-programming/exploring-movablecontentof-in-jetpack-compose-6807a43047cd
        - https://victorbrandalise.com/using-movablecontentof/
        - https://newsletter.jorgecastillo.dev/p/movablecontentof-and-movablecontentwithreceivero

    É uma função do jetpack compose que permite mover uma parte da interface (e seu estado interno) para uma nova
    posicao na arvore de composeicao sem recria-la do zero (sem recomposiecao ou perda de estado)

    Casos de uso comuns
        - Telas responsivas: Alterar um layout  de tela cheia entre Row e Column dependendo da orientacao do
        dispositivo, mantendo o estado interno dos elementos

        - Transicao entre telas: Mvoer elementos visuais compartilhados
            - Barras de navegacao entre diferentes rotas de navegacao

       Como funciona
        - Empacota o conteudo: Envolve o bloco Composable com movableContentOf e o armazena numa variavel
        - Move sem resetar: Quando a variavel é invocada noutro lugr da tela,  Compose
        nao destroi e recria o componente.
 */

@Composable
fun MovableCounterComponent(
    modifier: Modifier = Modifier,
    isLandscape: Boolean
) {
    /*
        - Conteúdo que possui um estado interno
        - Usamos o remember para que o movableContentOf nao seja recriado.
     */
    val counterComponentState = remember {
        movableContentOf {
            CounterComponent()
        }
    }

    if (isLandscape) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Landscape",
                modifier = Modifier.padding(
                    start = 8.dp,
                    end = 8.dp
                ),
                style = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                ),
            )
            counterComponentState()
        }
    } else {
        Column(modifier) {
            Text(
                "Portrait",
                style = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp, bottom = 8.dp)
            )
            counterComponentState()
        }
    }
}

@Composable
private fun CounterComponent() {
    // Estado local
    var counter by rememberSaveable { mutableIntStateOf(0) }
    Column {
        Text(
            text = "Counter: $counter",
            style = TextStyle(
                fontSize = 23.sp,
                textAlign = TextAlign.Center
            )
        )
        Button(onClick = { counter++ }) {
            Text(text = "Increment")
        }
    }
}

@Preview(
    showBackground = true,
    name = "Portrait",
    device = "spec:width=360dp,height=640dp,dpi=480,orientation=portrait"
)
@Composable
fun MovableCounterComponentPortraitPreview() {
    JustComposeLabsTheme {
        MovableCounterComponent(Modifier, isLandscape = false)
    }
}

@Preview(
    showBackground = true,
    name = "Landscape",
    device = "spec:width=640dp,height=360dp,dpi=480,orientation=landscape"
)
@Composable
fun MovableCounterComponentLandscapePreview() {
    JustComposeLabsTheme {
        MovableCounterComponent(Modifier, isLandscape = true)
    }
}
