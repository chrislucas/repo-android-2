package com.br.justcomposelabs.tutorial.gptstudies.changegridlayout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/*
    TODO explorar esse codigo
 */
// Enum para definir os tipos de layout
enum class LayoutType {
    LIST,
    GRID_FIXED,
    GRID_FLEXIBLE,
}

// Componente principal
@Composable
@Preview(showBackground = true)
fun LayoutDemo() {
    val items = remember { List(20) { "Item $it" } }
    var selectedLayout by remember { mutableStateOf(LayoutType.LIST) }
    var expanded by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            LayoutTopBar(
                onLayoutChange = {
                    selectedLayout = it
                    expanded = false
                },
                expanded = expanded,
                onExpandChange = { expanded = it },
            )
        },
    ) { innerPadding ->
        LayoutContent(
            items = items,
            layoutType = selectedLayout,
            modifier =
            Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        )
    }
}

// Componente para a TopBar com menu
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutTopBar(
    onLayoutChange: (LayoutType) -> Unit,
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
) {
    TopAppBar(title = { Text("Layout Menu Demo") }, actions = {
        Box {
            TextButton(onClick = { onExpandChange(true) }) {
                Text("Change Layout")
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { onExpandChange(false) }) {
                DropdownMenuItem(onClick = { onLayoutChange(LayoutType.LIST) }, text = {
                    Text("List")
                })
                DropdownMenuItem(onClick = { onLayoutChange(LayoutType.GRID_FIXED) }, text = {
                    Text("Grid (Fixed)")
                })
                DropdownMenuItem(onClick = { onLayoutChange(LayoutType.GRID_FLEXIBLE) }, text = {
                    Text("Grid (Flexible)")
                })
            }
        }
    })
}
