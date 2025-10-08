package com.br.justcomposelabs.tutorial.gptstudies

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

/*
    TODO explorar esse codigo
 */
// Enum para definir os tipos de layout
enum class LayoutType {
    LIST, GRID_FIXED, GRID_FLEXIBLE
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
                onLayoutChange = { selectedLayout = it; expanded = false },
                expanded = expanded,
                onExpandChange = { expanded = it })
        }) { innerPadding ->
        LayoutContent(
            items = items,
            layoutType = selectedLayout,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

// Componente para a TopBar com menu
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutTopBar(
    onLayoutChange: (LayoutType) -> Unit,
    expanded: Boolean, onExpandChange: (Boolean) -> Unit
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

// Componente que decide qual layout exibir
@Composable
fun LayoutContent(items: List<String>, layoutType: LayoutType, modifier: Modifier = Modifier) {
    when (layoutType) {
        LayoutType.LIST -> ListLayout(items, modifier)
        LayoutType.GRID_FIXED -> GridFixedLayout(items, modifier)
        LayoutType.GRID_FLEXIBLE -> GridFlexibleLayout(items, modifier)
    }
}

// Lista simples usando Material3
@Composable
fun ListLayout(items: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier, contentPadding = PaddingValues(8.dp)) {
        items(items.size) { index ->
            ListItemCard(items[index])
        }
    }
}

// Grid com duas colunas fixas
@Composable
fun GridFixedLayout(items: List<String>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), modifier = modifier, contentPadding = PaddingValues(8.dp)
    ) {
        items(items.size) { index ->
            GridItemCard(items[index])
        }
    }
}

// Grid com colunas adaptativas
@Composable
fun GridFlexibleLayout(items: List<String>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(120.dp),
        modifier = modifier,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items.size) { index ->
            GridItemCard(items[index])
        }
    }
}

// Cartão para itens de lista usando Material3
@Composable
fun ListItemCard(text: String) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { /* ação ao clicar */ },
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier.padding(16.dp), contentAlignment = Alignment.CenterStart
        ) {
            Text(text)
        }
    }
}

// Cartão para itens de grid usando Material3
@Composable
fun GridItemCard(text: String) {
    val ctx = LocalContext.current

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(4.dp)
            .clickable {
                Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show()
            },
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text)
        }
    }
}
