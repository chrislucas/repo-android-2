package com.br.justcomposelabs.tutorial.google.compose.components.scaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.DrawerValue
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/*
    ScaffoldState: https://developer.android.com/reference/kotlin/androidx/compose/material/ScaffoldState
        -  Estado para o componente composable Scaffold

    ScaffoldState(
        drawerState: DrawerState,
        snackbarHostState: SnackbarHostState
    )

     O que é o Scaffold ?
        - É um layout quye implementa o básico da estrutura do layout
         material design.
         - Podemos adicionar componentes como TopBar, BottomBar, Fab, Drawe

    https://foso.github.io/Jetpack-Compose-Playground/material/scaffold/
 */

@Preview(showBackground = true)
@Composable
fun SimpleDrawerScaffoldComponent() {
    val bgColor = Color(0xFF1976D2)
    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(DrawerValue.Open)
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {},
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add"
                    )
                }
            }
        },
        drawerContent = {
            Text(text = "Drawer Content")
        },
        content = { paddingValues ->
            Text(
                "Body Content",
                modifier = Modifier.padding(paddingValues)
            )
        },
        bottomBar = {
            BottomAppBar(backgroundColor = bgColor) {
                Text("BottomAppBar")
            }
        }
    )
}
