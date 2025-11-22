package com.br.justcomposelabs.tutorial.google.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.R

/*
    https://developer.android.com/develop/ui/compose/graphics/images/compare
 */


@Preview(showBackground = true)
@Composable
private fun LoadImage() {
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.honeycomb),
            contentDescription = null
        )
    }
}