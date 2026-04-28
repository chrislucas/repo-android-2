package com.br.justcomposelabs.tutorial.google.codelabs.state.advancedstateandsideeffects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br.justcomposelabs.utils.composable.fillMaxSizePadding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

/*
    https://developer.android.com/codelabs/jetpack-compose-advanced-state-side-effects
 */

/*
    A pergunta
    using explicity StateFlow<T> is not enough to make sure that uiState is read only
 */

class UiUnsafeStateViewModel : ViewModel() {
    // Private mutable backing property (only ViewModel can change)
    private val _uiState = MutableStateFlow(0)

    /*
        O teste é verificar se é possivel fazer o casting para um MutableStateFlow
        fora da classe UiUnsafeState, o que permitiria alterar o valor de uiState
        diretamente.
     */
    // Public read-only property for the UI
    val uiState: StateFlow<Int> = _uiState // .asStateFlow()

    fun updateData(operation: () -> Int) {
        // Logic to update the state
        _uiState.value = operation()
    }
}

@Preview(showBackground = true)
@Composable
fun UiUnsafeStateV(viewModel: UiUnsafeStateViewModel = viewModel()) {
    val uiState = viewModel.uiState as MutableStateFlow<Int>
    val unsafeUiState by uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSizePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Info: $unsafeUiState",
            style = TextStyle(
                fontSize = 34.sp
            ),
            modifier = Modifier.padding(20.dp)
        )
        IconButton(onClick = {
            /*
                viewModel.updateData {
                    Random.nextInt(0, 100)
                }

                Isso só é possível por causa do casting as MutableStateFlow<Int>
                e o casting por sua vez só é possivel pois na viewModel o objeto
                que cuida do estado é mutável e o objeto que exposto pela ViewModel
                para observar a mudanca apesar de ser um StateFlow sua instância
                é do tipo MutableStateFlow, o que permite realizar o casting
                mesmo que inseguro,

                Para evitarmos a possibilidade do casting usamos
                 - val uiState: StateFlow<Int> = _uiState.asStateFlow()
                 - isso cria um ReadonlyStateFlow Imutavel que é uma subclasse de StateFlow

             */

            uiState.value = Random.nextInt(0, 100)
        }, modifier = Modifier.size(50.dp)) {
            Icon(
                Icons.Filled.Update,
                contentDescription = "Update"
            )
        }
    }
}
