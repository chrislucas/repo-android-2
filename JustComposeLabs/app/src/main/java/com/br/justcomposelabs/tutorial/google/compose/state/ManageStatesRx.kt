package com.br.justcomposelabs.tutorial.google.compose.state

import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject


/*

    Other supported types of state
    https://developer.android.com/develop/ui/compose/state#use-other-types-of-state-in-jetpack-compose
        -

    Extension functions
        - https://developer.android.com/reference/kotlin/androidx/compose/runtime/rxjava2/package-summary#extension-functions
 */

class HelloStateRxViewModel : ViewModel() {
    private val state: BehaviorSubject<String> = BehaviorSubject.createDefault("")

    /*

     */
    val stateObservable: Observable<String> = state.hide()

    fun onDataChange(data: String) {
        state.onNext(data)
    }
}


@Preview(showSystemUi = true, name = "HelloScreenRx")
@Composable
fun HelloScreenRx(helloStateRxViewModel: HelloStateRxViewModel = viewModel()) {
    val name by helloStateRxViewModel.stateObservable.subscribeAsState("")
    HelloContentPresentation(name, helloStateRxViewModel::onDataChange)
}

@Composable
private fun HelloContentPresentation(
    value: String = "",
    onValueChange: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 1.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Center
        ) {
            if (value.isNotBlank()) {
                Text(
                    text = "Hello, $value!",
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                label = { Text("label") }
            )
        }
    }
}