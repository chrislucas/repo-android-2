package com.br.funwithviewmodel.tutorials.google.lifecycleaware.viewmodelscope

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


/*
    Add KTX dependencies
    https://developer.android.com/topic/libraries/architecture/coroutines#dependencies

    The built-in coroutine scopes described in this topic are contained in the
    KTX extensions for each corresponding component.
    Be sure to add the appropriate dependencies when using these scopes.

    For ViewModelScope, use androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0 or higher.
    For LifecycleScope, use androidx.lifecycle:lifecycle-runtime-ktx:2.4.0 or higher.
    For liveData, use androidx.lifecycle:lifecycle-livedata-ktx:2.4.0 or higher.
 */
class SampleViewModel(initFun: suspend () -> Unit) : ViewModel() {

    /*
        Lifecycle-aware coroutine scopes

        Lifecycle aware componentes define alguns built-in scopes que podemos
        usar.

        VIewModelScopes
        LifeCycleScipe
     */

    init {
        viewModelScope.launch {
            initFun()
        }
    }
}