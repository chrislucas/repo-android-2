package com.br.funwithjetpackcompose.tutorials.google.foundation.resources

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import com.br.mylibrary.R

@Preview(showBackground = true)
@Composable
fun StringPlurals() {
    Column {
        Text(text = pluralStringResource(R.plurals.minute_plural, 1, 1))
        Text(text = pluralStringResource(R.plurals.minute_plural, 2, 110))
    }
}