package com.br.composeshapes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.composeshapes.ui.theme.ComposeShapesTheme


/*
    https://foso.github.io/Jetpack-Compose-Playground/foundation/shape/
    https://developer.android.com/develop/ui/compose/graphics/draw/shapes
    https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-shapes/
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeShapesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListShapes(
                        modifier = Modifier.padding(innerPadding),
                        listOf()
                    )
                }
            }
        }
    }
}

@Composable
fun ListShapes(
    modifier: Modifier = Modifier,
    composables: List<@Composable () -> Unit>
) {
    LazyColumn(modifier) {
        items(composables) { composable ->
            ItemComposable(composable)
        }
    }
}

@Composable
fun ItemComposable(content: @Composable () -> Unit) {
    Card(modifier = Modifier.padding(4.dp)) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeShapesTheme {

        val composables = buildList<@Composable () -> Unit> {
            add({
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.Red)
                    )
                }
            })

            add({
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CutCornerShape(10.dp))
                            .background(Color.Red)
                    )
                }
            })
        }

        ListShapes(modifier = Modifier, composables)
    }
}