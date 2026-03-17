package com.br.funwithjetpackcompose.tutorials.google.foundation.resources

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.br.mylibrary.R

/*
    TODO estudar
    https://developer.android.com/develop/ui/compose/resources
 */


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StringResource() {
    Text(
        text = stringResource(R.string.test_string_resource, "New Year", 2021)
    )
}