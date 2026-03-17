package com.br.composerecomposition.tutorials.google.state.remember

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/*
    Pesquisar por
    using remember android compose
    - em Compose, remember function é uma api CORE usada para armazenar objetos em memoria
    durante a primeira composicao/construcao da UI, persistindo o ultimo valor de um objeto
    durante as subsequentes reomposicoes da funcao composable.

        - Esse recurso é util para evitar a re-criacao de objetos durante a recomposicao. Muitas
        vezes a criacao de um objeto é um custo computacional elevado

    Conceitos Chaves
        - Persistence across Recomposition
            - Quando o usuario interage com a UI e o estado do contador
            muda, Compose re-run a funcao composable afetada, evento chamado
            de recomposition
            - remember armazena o último valor da variavel usada como contador

        - Tied to Composition Hierarchy
            - Valores armazenados pela funcao remember estao atrelados(tied) com o local
            especifico da funcao composable da composicao.

            - Se a funcao composable for removida da composition (isto é,
            um if(shouldShow) por exemplo), o valor armazenado é apagado

        - State vs Events
            - O estado (countState, countStateByDelegate) determina o que sera exibido na UI, enquanto
            o evento, clique no botão, é o mecanismos que muda o estado

        - When to Use RememberSaveable ?
            - Remember armazena o estado de um objeto somente enquanto a Activity estiver
            viva em memoria, se precisarmos que o estado sobreviva a mudancas de configuracao, (screen
            rotation por exemplo) usamos rememberSaveable
 */



@Preview(showBackground = true)
@Composable
fun AllCounters() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WaterCounter()
        WaterCounterByDelegate()
        WaterCounterByDestructuring()
    }
}


@Preview(showBackground = true)
@Composable
fun WaterCounter() {

    // usando mutableState object diretamente
    val countState = remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Contador ${countState.intValue}")
        Button(onClick = { countState.intValue++ }) {
            Text("Add one")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WaterCounterByDelegate() {

    // usando property delegation
    var countStateByDelegate by remember { mutableFloatStateOf(0f) }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Contador: $countStateByDelegate")
        Button(onClick = { countStateByDelegate += .33f }) {
            Text("Add one")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WaterCounterByDestructuring() {

    // usando property delegation
    val (counterState, setter) = remember { mutableFloatStateOf(0f) }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Contador: $counterState")
        Button(onClick = { setter(counterState + .33f) }) {
            Text("Add one")
        }
    }
}