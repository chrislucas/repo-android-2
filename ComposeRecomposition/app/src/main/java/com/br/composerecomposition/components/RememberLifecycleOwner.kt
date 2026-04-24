package com.br.composerecomposition.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.rememberLifecycleOwner

/*
    Version 2.10.0
    https://developer.android.com/jetpack/androidx/releases/lifecycle#2.10.0

    - RememberLifecycleOwner
        - Composable para disponibilizar a criacao de escopos de lifecycle diretamente dentro de uma UI
        - Isso é util para componentes que precisam gerenciar seus proprios ciclos-de-vida,
        tais como, HorizontalPager

 */


@Composable
fun LifecycleAwareComponent() {
    /*
        Esse lifecycle é automaticamente movido para o estado DESTROYED quando
        ele deixa a composicao e o seu maxLifecycle atinge o estado passado como
        parâmetro ou seu valor maximo ou o Lifecycle.State de seu componente pai/parent
     */
    val lifecycleOwner = rememberLifecycleOwner(
        maxLifecycle = Lifecycle.State.RESUMED,
        parent = LocalLifecycleOwner.current
    )

    CompositionLocalProvider(LocalLifecycleOwner.provides(lifecycleOwner) ) {
        val childLifecycleOwner = LocalLifecycleOwner.current
        Log.i("COMP_LOCAL_PROVIDER", "$childLifecycleOwner")
    }
}
