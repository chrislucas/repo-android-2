package com.br.funwithdatabinding.view.features.tutorials.google.docs.compose.configuretextfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
private fun FunTextField(modifier: Modifier = Modifier) {

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column(modifier = modifier){
        TextField(
            value = textFieldValue ,
            onValueChange = {
                textFieldValue = it
            } ,
            placeholder = {
                Text("TextField")
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "TextField")
@Composable
private fun ShowFunTextField() {
    MaterialTheme {
        FunTextField(Modifier.fillMaxSize())
    }
}