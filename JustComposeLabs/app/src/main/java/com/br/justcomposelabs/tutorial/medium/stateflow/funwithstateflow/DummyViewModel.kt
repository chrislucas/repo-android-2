package com.br.justcomposelabs.tutorial.medium.stateflow.funwithstateflow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


/**
 * TODO
 * @see com.br.justcomposelabs.tutorial.medium.funwithflowtype.FunWithFlowTypesActivity
    https://nameisjayant.medium.com/kotlin-stateflow-explained-2c4a102b0595
 */
class DummyViewModel(): ViewModel() {

    private val state: MutableStateFlow<String> = MutableStateFlow("Init Value")
    val stateObservable: StateFlow<String> = state // state.asStateFlow()
}