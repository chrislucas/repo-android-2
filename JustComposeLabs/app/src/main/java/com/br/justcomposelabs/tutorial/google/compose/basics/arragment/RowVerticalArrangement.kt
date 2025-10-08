package com.br.justcomposelabs.tutorial.google.compose.basics.arragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun RowVerticalArrangement() {
    val verticalArrangement = listOf(
        Alignment.Top,
        Alignment.CenterVertically,
        Alignment.Bottom
    )
    Column (modifier = Modifier.fillMaxWidth()) {

        Row {
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

        LazyColumn {
            items(verticalArrangement) { arrangement ->
                Row (verticalAlignment = arrangement) {
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


