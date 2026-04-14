package com.br.justcomposelabs.tutorial.google.compose.state.wheretohoiststate

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable

/*
    https://developer.android.com/develop/ui/compose/state-saving#ui-logic
    - rememberSaveable armazena estado de elementos UI num Bundle atraves do mecanismos
    de armazenamento

        - rememberSaveable é capaz de armazenar tipos primitivos automaticamente
        - Se o estado é armazenado num tipo nao primitivo, como uma data clas, podemos
        usar outros tipos de mecanimos de armazenamento
            - Parcelize annotation
            - listSaver, mapSaver
                - https://developer.android.com/reference/kotlin/androidx/compose/runtime/saveable/package-summary#listSaver(kotlin.Function2,kotlin.Function1)

 */

@Composable
fun rememberLazyListSate(
    initialFistVisibleItemIndex: Int = 0,
    initialFistVisibleItemScrollOffset: Int = 0
): LazyListState = rememberSaveable(saver = LazyListState.Saver) {
    LazyListState(
        initialFistVisibleItemIndex,
        initialFistVisibleItemScrollOffset
    )
}
