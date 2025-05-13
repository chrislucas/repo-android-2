package com.br.funwithjetpackcompose.tutorials.google.news.april25

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.TextFieldValue

import androidx.compose.ui.autofill.ContentType

@Composable
fun Test(modifier: Modifier = Modifier) {

    Box(modifier = modifier) {
        var textFieldValue = remember {
            mutableStateOf(TextFieldValue(""))
        }
        // [START android_compose_autofill_1]
        TextField(
            value = textFieldValue.value,
            onValueChange = {textFieldValue.value = it},
            modifier = Modifier.semantics { contentType = ContentType.Username }
        )
    }

}