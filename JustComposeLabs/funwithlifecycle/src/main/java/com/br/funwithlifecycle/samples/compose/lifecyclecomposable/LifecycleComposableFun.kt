package com.br.funwithlifecycle.samples.compose.lifecyclecomposable

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.rememberLifecycleOwner


/*
    https://developer.android.com/jetpack/androidx/releases/lifecycle

    Lifecyclw-awae componet
       - Um componente execuca acoes em resposa a mudanca do estad do filfecycle de um outro componente
       como uma activity ou fragmnet.

       - Esse componente ajuda a produzir melhor organizacao e frequentemente um codito
       mais leve e facil de manter
 */

@Composable
fun LifecycleAwareComponent() {
    /*
        Important changes since 2.9.0:
        rememberLifecycleOwner permite criar um escopoe de lifecycle diretamente dentro da UI

        Isso é util para componentes que precisam gerenciar seu proprio ciclo de vida independentemente
        como HorizontalPager que querem retornar a pagina atual no estado RESUMED ou
        bibliotecas como navigation 3
     */


    /*
        Esse lifecycle e automaticamente levado para o estado DESTROYED quando ele deixa a composition
        e seu maxLifecycle atinge o maximo do lifeycle que foi definido, no caso abaixo RESUMED,
        ou o atinge o estado do lifecycle de seu componente pai/parent
     */
    val lifecycleOwner = rememberLifecycleOwner(
        maxLifecycle = Lifecycle.State.RESUMED,
        parent = LocalLifecycleOwner.current
    )

    CompositionLocalProvider(LocalLifecycleOwner provides lifecycleOwner) {
        val childLifecycleOwner = LocalLifecycleOwner.current

        Log.i("LIFECYCLE_AWARE", "child lifecycle owner: $childLifecycleOwner")
    }
}
