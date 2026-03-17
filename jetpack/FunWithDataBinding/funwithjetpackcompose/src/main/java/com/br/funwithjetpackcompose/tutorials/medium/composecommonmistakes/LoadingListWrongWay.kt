package com.br.funwithjetpackcompose.tutorials.medium.composecommonmistakes

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoadingListWrongWay(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val names = mutableListOf<String>()
    scope.launch {
        fetchNames { values ->
            names.addAll(values)
        }
    }

    LazyColumn(modifier) {
        items(names) {
            Text(it)
        }
    }
}