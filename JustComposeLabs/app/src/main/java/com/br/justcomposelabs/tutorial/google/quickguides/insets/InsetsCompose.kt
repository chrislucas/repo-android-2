package com.br.justcomposelabs.tutorial.google.quickguides.insets

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Euro
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/*
    https://developer.android.com/develop/ui/compose/quick-guides/content/video/insets-in-compose

    Insets
        - Prove informacoee sobre sytem UI para garantir que o app desenhe na area correta e
        que a UI nao seja sobreposta/escondida pelo system UI (toolbar, relogio)

        - Esse link ensia como a api de Insets comunica para o app onde o system decorations esta
        posto e como a API Compose ajuda o conteudo a se mover com o system bar, keyboard e a
        taskbar
 */


@Preview
@Composable
fun BottomAppBarPreview() {
    /*
        https://developer.android.com/develop/ui/compose/quick-guides/content/display-bottom-app-bar
     */

    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Check, "Check")
                    }

                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Edit, "Edit")
                    }

                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Mic, "Mic")
                    }

                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Image, "Image")
                    }

                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Euro, "Euro")
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {},
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(imageVector = Icons.Filled.Add, "Add")
                    }
                }
            )
        },

        ) { innerPadding ->
        Text(modifier = Modifier.padding(innerPadding), text = "Bottom app bar")
    }
}