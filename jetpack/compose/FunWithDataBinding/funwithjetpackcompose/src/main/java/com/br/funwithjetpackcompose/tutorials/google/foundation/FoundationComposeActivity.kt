package com.br.funwithjetpackcompose.tutorials.google.foundation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.funwithjetpackcompose.tutorials.google.foundation.ui.theme.FunWithDataBindingTheme

/*
    TODO
    https://developer.android.com/develop/ui/compose/documentation

    https://developer.android.com/develop/ui/compose/mental-model
 */
class FoundationComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowScaffold()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
private fun ShowGreeting() {
    FunWithDataBindingTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Greeting(
                name = "Android",
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowScaffold() {
    /*
        https://developer.android.com/develop/ui/compose/components/scaffold
        Em material Design, Scaffold é uma estrutura fundamenta que provêm uma padronizacao
        para UI complexa

        - Ela envolve diferentes partes da UI juntas, tais como app bars e o floating
        action button, dando um coerente "aparencia" ao app

        TopAppBar
        - https://developer.android.com/develop/ui/compose/components/app-bars?hl=pt-br
        - https://medium.com/@sedakundakitchen/creating-a-top-app-bar-with-material-design-3-jetpack-compose-1d7fe3c97d12

        TopAppBarColors
        - https://developer.android.com/reference/kotlin/androidx/compose/material3/TopAppBarColors
     */

    var observableAddButtonPress by remember { mutableIntStateOf(0) }


    FunWithDataBindingTheme {
        Scaffold(
            topBar = providerTopAppBar(),
            bottomBar = providerBottom(),
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(onClick = { observableAddButtonPress++ }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text =
                        """
                            ${observableAddButtonPress}x que o botão + foi pressionado
                        """.trimIndent()
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
private fun providerTopAppBar(): @Composable () -> Unit = {
    TopAppBar(
        title = { Text("Top App Bar") },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}

private fun providerBottom(): @Composable () -> Unit = {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Bottom App Bar"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShowScaffold()
}