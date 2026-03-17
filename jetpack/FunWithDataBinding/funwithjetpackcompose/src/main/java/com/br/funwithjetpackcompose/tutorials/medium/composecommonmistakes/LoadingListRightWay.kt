package com.br.funwithjetpackcompose.tutorials.medium.composecommonmistakes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun LoadingListRightWay(modifier: Modifier = Modifier.Companion) {
    var names by remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(true) {
        fetchNames { values -> names = values }
    }

    LazyColumn(modifier) {
        items(names) { Text("Name: $it") }
    }
}