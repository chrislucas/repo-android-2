package com.br.funwithjetpackcompose.tutorials.medium.rememberfunction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.funwithjetpackcompose.tutorials.playground.ui.theme.FunWithDataBindingTheme

/*
    https://medium.com/@williamrai13/jetpack-compose-understanding-remember-function-b518ec5687fc
    Remember é uma funcao em compose usada para lembrar o estado "durante"
    (across recomposition) a recomposicao de um composable

    - De maneira simples, essa funcao observa as informacao contida na tela para nao perde-la
    cada vez que a tela for atualizada

 */


@Preview(showBackground = true, showSystemUi = true, name = "remember experiment")
@Composable
fun RememberExperiment(modifier: Modifier = Modifier) {
    FunWithDataBindingTheme {
        /*
            Variavel de estado mutavel do tipo inteira, observada
            pelo compilador compose. Sempre que essa variavel
            mudar, Compose ativa a recomposition function onde essa
            variavel estiver alocada


            val counter = mutableStateOf(0)

            - o codigo acima possui um erro, ao clicar no botao
            e tentar incrementar a variavel counter, isso nao ocorre

                - Ao clicar no botao incrementamos o contador para 1
                mas com o mecanismos da recomposicao, o seu valor retorna
                para zero pois nao usamos a funcao remember

         */
        val counter = remember { mutableIntStateOf(0) }

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Counter: ${counter.value}")
            Button(onClick = {
                counter.value++
                println("Counter: ${counter.value}")
            }) {
                Text(text = "+")
            }
        }
    }
}