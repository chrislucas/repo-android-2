package com.br.funwithdatabinding.view.features.tutorials.google.docs.composable.textfield.valuebased

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

/*
    https://developer.android.com/develop/ui/compose/text/migrate-state-based
 */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StateBasedTextField() {
    var state by rememberSaveable { mutableStateOf("") }
    TextField(
        value = state,
        onValueChange = { state = it },
        singleLine = true,
    )
}