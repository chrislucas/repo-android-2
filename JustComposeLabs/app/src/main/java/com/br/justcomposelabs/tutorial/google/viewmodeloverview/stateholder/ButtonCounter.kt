package com.br.justcomposelabs.tutorial.google.viewmodeloverview.stateholder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/*
    https://developer.android.com/topic/architecture/ui-layer/stateholders
 */


@Preview(showBackground = true)
@Composable
fun CounterButton() {
    var count by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { count++ }) {
            Text("I've been incremented $count times")
        }

        Button(onClick = { count-- }) {
            Text("I've been decremented $count times")
        }
    }

}