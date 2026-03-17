package com.br.funwithjetpackcompose.tutorials.google.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage

/*
    https://developer.android.com/develop/ui/compose/graphics/images/loading
 */
@Preview(showBackground = true)
@Composable
fun ProfileImageAsync() {
    AsyncImage(
        model = "https://avatars.githubusercontent.com/u/122902271?v=4",
        contentDescription = "github_image_profile"
    )
}