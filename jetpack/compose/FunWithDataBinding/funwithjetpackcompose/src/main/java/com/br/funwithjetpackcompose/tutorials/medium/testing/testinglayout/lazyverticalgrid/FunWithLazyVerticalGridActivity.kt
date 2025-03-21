package com.br.funwithjetpackcompose.tutorials.medium.testing.testinglayout.lazyverticalgrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.funwithjetpackcompose.tutorials.medium.testing.testinglayout.lazyverticalgrid.ui.theme.FunWithDataBindingTheme

/*
    LazyVerticalGrid
    https://developer.android.com/reference/kotlin/androidx/compose/foundation/lazy/grid/package-summary#LazyVerticalGrid(androidx.compose.foundation.lazy.grid.GridCells,androidx.compose.ui.Modifier,androidx.compose.foundation.lazy.grid.LazyGridState,androidx.compose.foundation.layout.PaddingValues,kotlin.Boolean,androidx.compose.foundation.layout.Arrangement.Vertical,androidx.compose.foundation.layout.Arrangement.Horizontal,androidx.compose.foundation.gestures.FlingBehavior,kotlin.Boolean,androidx.compose.foundation.OverscrollEffect,kotlin.Function1)
 */
class FunWithLazyVerticalGridActivity : ComponentActivity() {


    private val itemsList = (0..5).toList()
    private val itemsIndexedList = listOf("A", "B", "C")
    private val itemModifier = Modifier.border(1.dp, Color.Blue).height(80.dp).wrapContentSize()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Modifier.padding(innerPadding)
                }
            }
        }
    }
}

/*
@Composable
private fun LazyVerticalGrid( columns = GridCells.Fixed(3)) {
    items(itemsList) { Text("Item is $it", itemModifier) }
    item { Text("Single item", itemModifier) }
    itemsIndexed(itemsIndexedList) { index, item ->
        Text("Item at index $index is $item", itemModifier)
    }
}

 */

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
    }
}