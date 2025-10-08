package com.br.justcomposelabs.tutorial.google.quickguides.scaffoldcomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/*
    https://developer.android.com/develop/ui/compose/quick-guides/collections/display-interactive-components
    https://developer.android.com/develop/ui/compose/quick-guides/content/create-scaffold

 */


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun ScaffoldComponentTopAppBar() {
    var presses by remember { mutableIntStateOf(0) }
    /*
        https://developer.android.com/develop/ui/compose/quick-guides/content/create-scaffold

        Scaffold
            - Uma estrutura fundamental que prover padronizacao para complexas interfaces de usuario
            - Ela mantem junto partes de UI como appbars e floating action buttons, dando ao app
            um coerente Look and Feel

        - Pontos chave
            -  Scaffold composable prove uma API direta para construir rapidamente
            uma estrutura para o app de acordo com Material Design Guidelines
            - Aceita varios composable como parametro, topBar, bottomBar e floatingActionButton


        https://developer.android.com/develop/ui/compose/components/scaffold?hl=pt-br
     */
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = colorScheme.primaryContainer,
                    titleContentColor = colorScheme.primary,
                ),
                title = {
                    Text("Top app bar")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = colorScheme.primaryContainer,
                contentColor = colorScheme.primary,

                ) {
                Text("Bottom app bar")
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
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
                text = """
                    This is an example of a scaffold. It uses the Scaffold composable's 
                    parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button $presses times.
                """.trimIndent(),
            )
        }
    }
}