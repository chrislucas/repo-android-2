package com.br.kmm.multilanguagesupport.tutorials.google.composables.scaffold


/*

 */

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
    // https://composables.com/material3/scaffold

    Scaffold implementa o basico da estrutura Visual de Material Design Layout
    - Prove a api para colocar junto muitos dos componentes do Material para construir telas
    - Garante uma estrategeia apropriada para construir o layout e colata dados para que
    os componentes funcionem juntos
 */




@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SimpleScaffoldWithTopBar() {
    val colors = listOf(
        Color(0xFFffd7d7.toInt()),
        Color(0xFFffe9d6.toInt()),
        Color(0xFFfffbd0.toInt()),
        Color(0xFFe3ffd9.toInt()),
        Color(0xFFd0fff8.toInt())
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Simple Scaffold With TopBar") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {}) {
                Text("+")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier.consumeWindowInsets(innerPadding),
                contentPadding = innerPadding
            ) {
                items(colors.size) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(colors[index % colors.size])
                    )
                }
            }
        }
    )
}