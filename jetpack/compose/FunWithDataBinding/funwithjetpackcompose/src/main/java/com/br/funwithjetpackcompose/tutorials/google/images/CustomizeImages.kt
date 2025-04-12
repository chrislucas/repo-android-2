package com.br.funwithjetpackcompose.tutorials.google.images

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.mylibrary.R

/*
    TODO
    https://developer.android.com/develop/ui/compose/graphics/images/customize

    https://developer.android.com/develop/ui/compose/graphics/images/loading
 */
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SampleImage(modifier: Modifier = Modifier) {
    modifier
        .size(150.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Yellow).also {
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = stringResource(id = R.string.image_placehholder_description),
                contentScale = ContentScale.Fit,
                modifier = it
            )
        }
}