package com.br.funwithjetpackcompose.tutorials.google.foundation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.br.mylibrary.R

/*
    https://developer.android.com/develop/ui/compose/layouts/basics
 */


/*
    Row
 */
@Preview(showBackground = true)
@Composable
fun LayoutRow(modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.placeholder),
            contentDescription = stringResource(R.string.image_placehholder_description)
        )

        Column {
            Text("Hello")
            Text("World")
        }
    }
}
