package com.br.justcomposelabs.tutorial.google.compose.state.stateflow

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class StateFlowSampleViewModel() : ViewModel() {
    /*
        https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/
     */

    private val mutableStateFlow = MutableStateFlow(0)

    val counter = mutableStateFlow.asStateFlow()

    fun incrementCounter() {
        viewModelScope.launch {
            mutableStateFlow.update { c ->
                c + 1
            }
        }
    }

    fun decrementCounter() {
        viewModelScope.launch {
            mutableStateFlow.update { c ->
                c - 1
            }
        }
    }

    fun resetCounter() {
        viewModelScope.launch {
            mutableStateFlow.update { 0 }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun ButtonUpdateStateFlow(
    viewModel: StateFlowSampleViewModel = StateFlowSampleViewModel()
) {
    val counter = viewModel.counter.collectAsState()

    Column (
        modifier =
            Modifier.fillMaxSize()
                .border(2.dp, Color.Red),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = { viewModel.incrementCounter() }) {
            Text("Increment: ${counter.value}")
        }

        Button(onClick = { viewModel.decrementCounter() }) {
            Text("Decrement: ${counter.value}")
        }

        Button(onClick = { viewModel.resetCounter() }) {
            Text("Reset: ${counter.value}")
        }
    }


}