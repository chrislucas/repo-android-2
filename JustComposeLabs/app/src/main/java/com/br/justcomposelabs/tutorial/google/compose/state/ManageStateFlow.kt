package com.br.justcomposelabs.tutorial.google.compose.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
@Preview(showSystemUi = true, name = "HelloScreenFlowViewModel")
fun HelloScreenFlow(helloStateFlowViewModel: HelloStateFlowViewModel = viewModel()) {
    val name by helloStateFlowViewModel.name.collectAsStateWithLifecycle()
    HelloContent(name, helloStateFlowViewModel::onNameChange)
}