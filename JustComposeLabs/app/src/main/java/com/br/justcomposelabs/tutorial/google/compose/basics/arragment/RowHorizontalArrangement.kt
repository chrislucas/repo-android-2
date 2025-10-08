package com.br.justcomposelabs.tutorial.google.compose.basics.arragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/*
    https://developer.android.com/develop/ui/compose/performance/bestpractices
 */

@Preview
@Composable
fun RowHorizontalArrangement() {
    val arrangements = listOf(
        Arrangement.Start,
        Arrangement.Center,
        Arrangement.End,
        Arrangement.SpaceBetween,
        Arrangement.SpaceAround,
        Arrangement.SpaceEvenly,
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
            items(arrangements) { arrangement ->
                Row (horizontalArrangement = arrangement) {
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


//@Preview
@Composable
fun RowArrangementRepeat() {
    /*
        Comparar performace de usar uma LazyColumn e um Repeat
     */

    val arrangements = listOf(
        Arrangement.Start,
        Arrangement.Center,
        Arrangement.End,
        Arrangement.SpaceBetween,
        Arrangement.SpaceAround,
        Arrangement.SpaceEvenly,
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
        arrangements.forEach { arrangement ->
            Row (horizontalArrangement = arrangement) {
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