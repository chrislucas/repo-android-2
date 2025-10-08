package com.br.justcomposelabs.tutorial.google.compose.basics.arragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ColumnVerticalArrangement() {
    val arrangements = listOf(
        Arrangement.Top,
        Arrangement.Center,
        Arrangement.Bottom,
        Arrangement.SpaceBetween,
        Arrangement.SpaceAround,
        Arrangement.SpaceEvenly,
    )

    Row(modifier = Modifier.Companion.fillMaxHeight()) {
        Column {
            Button(onClick = {}) {
                Text("A")
            }
            Button(onClick = {}) {
                Text("B")
            }
            Button(onClick = {}) {
                Text("C")
            }
        }

        LazyRow {
            items(arrangements) { arrangement ->
                Column(
                    verticalArrangement = arrangement
                ) {
                    Button(onClick = {}) {
                        Text("A")
                    }
                    Button(onClick = {}) {
                        Text("B")
                    }
                    Button(onClick = {}) {
                        Text("C")
                    }
                }
            }
        }
    }
}