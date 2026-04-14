package com.br.justcomposelabs.tutorial.google.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br.justcomposelabs.utils.composable.LogCompositions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * @see com.br.justcomposelabs.tutorial.google.compose.state.HelloStateFlowViewModel
 */

class DisplayTextViewModel() : ViewModel() {
    private val mutableContent: MutableStateFlow<String> = MutableStateFlow("")
    val content: StateFlow<String> = mutableContent

    fun onTapAppendContent(content: String) {
        mutableContent.update { it + content }
    }

    fun reset(value: String) {
        mutableContent.update { value }
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayAppendedTextComponent(viewModel: DisplayTextViewModel = viewModel()) {
    val contentState by viewModel.content.collectAsState()

    LogCompositions("DisplayAppendedTextComponent", contentState)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DisplayTextContent(contentState)
        UpdateDisplayTextContent(value = "+", viewModel::onTapAppendContent)
        UpdateDisplayTextContent(label = "Reset", value = "+", viewModel::reset)
    }
}
