package com.br.justcomposelabs.tutorial.google.compose.state


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import timber.log.Timber

/*
    https://stackoverflow.com/questions/66169601/what-is-the-difference-between-remember-and-mutablestate-in-android-jetpack

    - Remember: Permite fazer cache de estado de uma variavel entre as chamadas de recomposicoes
    - MutableState: observa e armazena o estado de uma variavel, porem entre recomposicoes
    uma nova instancia de MutableState é criada.

        - A combinacao dos 2 permite que guardemos o estado de variaveis durante a recomposicao



    val text = remember{ "" }
        - faz cache de uma string vazia

    val text = mutableStateOf("")
        - cria uma instancia de MutableState e o Compose observa seu valor, mas nao faz cache dessa instancia.
        , dessa maneira, toda vez que ocorre uma recomposicao um novo MutableState é criado
        - mas se eu tentar usar dessa forma o compilador emite um aviso e necessito usar uma annotation

    val text = remember {mutableStateOf("")}
        - remember faz caches da instancia de MutableState e
        mantem a mesma instancia em cada recomposition.



 */


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true, showSystemUi = true, name = "NoCacheStateBoxTextView")
@Composable
fun NoCacheStateBoxTextView() {
    /*
        Nao usar o remember faz com que toda recomposicao seja criada uma nova instancia de
        MutableState
     */
    val mutableStateField: MutableState<Int> = mutableIntStateOf(1)
    Box(
        modifier = Modifier
            .clickable { mutableStateField.value += 1 }
            .fillMaxSize()
            .padding(3.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Value: ${mutableStateField.value} | Ref: $mutableStateField")
    }
}


@Preview(showBackground = true, showSystemUi = true, name = "KeyCacheStateBoxTextView")
@Composable
fun KeyCacheStateBoxTextView() {
    val key = remember { 0 }
    var state by remember (key) { mutableIntStateOf(1) }

    Box(
        modifier = Modifier
            .clickable { state += 1 }
            .fillMaxSize()
            .padding(3.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Value: ${state} | Ref: $state| Ref Key: $key")
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "StateBoxTextView")
@Composable
fun StateBoxTextView() {
    val state: MutableState<Int> = remember { mutableIntStateOf(1) }
    Timber.tag("StateBoxTextView").d("Recomposition state: $state")
    Box(
        modifier = Modifier
            .clickable { state.value += 1 }
            .fillMaxSize()
            .padding(3.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Value: ${state.value} | Ref: $state"
        )
    }
}


@Preview(showBackground = true, showSystemUi = true, name = "StateSurfaceTextView")
@Composable
fun StateSurfaceTextView() {
    val state: MutableState<Int> = remember { mutableIntStateOf(1) }
    /*
        Nao ha uma forma de alinhar o conteudo dentro de uma Surface somente
        atraves dela, precisamos usar uma outra funcao composable que permita
        aplicar esse posicionamento.
            - Podemos usar Box, Column, Row
     */
    Surface(
        modifier = Modifier
            .clickable { state.value += 1 }
            .fillMaxSize()
            .padding(3.dp),
        shape = RectangleShape
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "Value: ${state.value} | Ref: $state"
            )
        }
    }

}