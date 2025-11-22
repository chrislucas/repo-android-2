package com.br.justcomposelabs.tutorial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

suspend fun fetchData(): String {
    delay(10000) // Simulate a network request or long-running operation
    return "Data from suspend function"
}


@Preview(showBackground = true)
@Composable
fun ParentComposable() {
    var data by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) { // 'Unit' means the effect will run once when the Composable enters Composition
        data = fetchData()
    }

    data?.let { ChildComposable(it) } ?: ChildComposable("Loading data...")
}

@Composable
fun ChildComposable(data: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            data,
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
    }
}