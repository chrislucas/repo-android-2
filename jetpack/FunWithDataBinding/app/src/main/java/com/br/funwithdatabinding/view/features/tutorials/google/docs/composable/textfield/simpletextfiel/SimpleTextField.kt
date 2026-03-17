package com.br.funwithdatabinding.view.features.tutorials.google.docs.composable.textfield.simpletextfiel

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SimpleTextFieldSample() {
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(""))
    }

    TextField(
        value = textFieldValue,
        onValueChange = { text ->
            textFieldValue = text
        },

        singleLine = true,
        placeholder = {
            Text(text = "Placeholder")
        },
        label = {
            Text(text = "Label")
        },
    )
}
