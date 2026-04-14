package com.br.justcomposelabs.tutorial.google.compose.state.withlifecycle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 *
 * @see com.br.justcomposelabs.tutorial.google.compose.state.StateTextField
 collectAsStateWithLifecycle
 https://developer.android.com/reference/kotlin/androidx/lifecycle/compose/package-summary#(kotlinx.coroutines.flow.StateFlow).collectAsStateWithLifecycle(androidx.lifecycle.LifecycleOwner,androidx.lifecycle.Lifecycle.State,kotlin.coroutines.CoroutineContext)
 https://developer.android.com/develop/ui/compose/text/user-input?textfield=state-based
 */

class TextState {
    private val mutableTextState = MutableStateFlow("")
    val uiState: StateFlow<String> = mutableTextState.asStateFlow()

    fun onChange(content: String) {
        mutableTextState.update {
            content
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextComponentInternalState() {
    /**
     * @see com.br.justcomposelabs.tutorial.google.compose.state.StateTextField
     * @see com.br.justcomposelabs.tutorial.google.compose.state.StateTextFieldRemember
     */

    val mutableStateContent = rememberSaveable { mutableStateOf("") }
    val (content, setContent) = mutableStateContent

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (content.isNotBlank()) {
            Text(
                text = "Hello, $content!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.headlineMedium
            )
        }
        OutlinedTextField(
            value = content,
            onValueChange = setContent,
            label = { Text("label") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextComponentExternalState() {
    val state = remember { TextState() }
    val uiState by state.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (uiState.isNotBlank()) {
            Text("Content: $uiState", style = TextStyle(fontSize = 23.sp))
        }

        OutlinedTextField(
            value = uiState,
            onValueChange = { content ->
                state.onChange(content)
            },
            label = {
                Text("TextField")
            }
        )
    }
}
