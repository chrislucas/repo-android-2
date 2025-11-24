package com.br.justcomposelabs.tutorial.google.compose.derivedstateof

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

/*
    derivedStateOf: convert one or multiple state objects into another state
    https://developer.android.com/develop/ui/compose/side-effects#derivedstateof

    In Compose, recomposisito corre cada vez que o estado de um objeto observado
    ou dado de entrada de um Composable muda.
        - Um estado de objeto ou dado de entrada pode ser modificado mais freqente que uma UI
        necessita ser atualizada, levando a recomposicoes desnecessarias

        - Nesses casos devemos usar a funcao derivedStateOf
            - Por exemplo: Um scroll

        - DerivedStateOf cria uma nova Compose State Object que pode ter sua
        atualizacao observada a quantidade o quanto precissamos

            - Funciona como o operator Kotlin Flows distinctUntilChanged() .
            Exemplo
            private fun checkDistinctUntilChanged() = runBlocking {

                val s = flow {
                    arrayOf(1, 1, 2, 2, 3, 1, 1, 2, 3, 3, 3).forEach {
                        delay(1000L)
                        emit(it)
                    }
                }

                s.distinctUntilChanged().collect {
                    /*
                        Nao emite valores repetidos consecutivos
                     */
                    println(it)
                }
            }

            fun main() {
                checkDistinctUntilChanged()
            }

 */

/**
 * @see com.br.justcomposelabs.tutorial.google.lazilyloadwithpaging.MessagesPreview
 */

data class Message(val content: String)

@Composable
fun MessageList(modifier: Modifier = Modifier, messages: List<Message>) {
    Box(modifier) {
        val listState = rememberLazyListState()

        LazyColumn(state = listState) {
            items(messages.size) { index ->
                Text(messages[index].content)
            }
        }


        val showButton by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 0
            }
        }


        AnimatedVisibility(visible = showButton) {
            ScrollTopButton(onClick = {
                listState.animateScrollToItem(0)
            })
        }
    }
}

@Composable
fun ScrollTopButton(onClick: suspend () -> Unit) {
    /*
        rememberCoroutineScope
        rememberCoroutineScope: obtain a composition-aware scope to launch a coroutine outside a composable
        https://developer.android.com/develop/ui/compose/side-effects#remembercoroutinescope
     */
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.navigationBarsPadding()) {
        Button(onClick = {
            scope.launch {
                onClick()
            }
        }) {
            Text("Scroll to Top")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MessageListPreview() {
    MessageList(
        modifier = Modifier.systemBarsPadding().navigationBarsPadding(),
        messages = buildList(100) {
            for (i in 1..100) {
                add(Message("Message $i"))
            }
        }
    )
}