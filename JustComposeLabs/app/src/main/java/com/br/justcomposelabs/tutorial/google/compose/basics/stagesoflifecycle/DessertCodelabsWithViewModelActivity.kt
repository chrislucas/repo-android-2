package com.br.justcomposelabs.tutorial.google.compose.basics.stagesoflifecycle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br.justcomposelabs.tutorial.google.compose.basics.stagesoflifecycle.ui.theme.JustComposeLabsTheme
import com.br.justcomposelabs.tutorial.google.compose.basics.stagesoflifecycle.viewmodel.DessertUiState
import com.br.justcomposelabs.tutorial.google.compose.basics.stagesoflifecycle.viewmodel.DessertViewModel

class DessertCodelabsWithViewModelActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DessertClickerApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DessertClickerApp(
    modifier: Modifier = Modifier,
    viewModel: DessertViewModel = viewModel()
) {
    val uiState by viewModel.observeDessertUiState.collectAsState()

    DessertClickerApp(
        modifier = modifier,
        uiState = uiState,
        onDessertClicked = viewModel::onDessertClicked
    )
}

@Composable
private fun DessertClickerApp(
    modifier: Modifier = Modifier,
    uiState: DessertUiState,
    onDessertClicked: () -> Unit
) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JustComposeLabsTheme {

    }
}