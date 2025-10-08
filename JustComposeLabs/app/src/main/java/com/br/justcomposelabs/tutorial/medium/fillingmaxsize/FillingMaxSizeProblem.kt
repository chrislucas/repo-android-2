package com.br.justcomposelabs.tutorial.medium.fillingmaxsize

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

/*
    https://medium.com/rocknnull/jetpack-compose-filling-max-width-or-height-94e3af129a7b
 */


@Composable
fun FillingMaxSizeProblem() {
    Row (
        modifier = Modifier
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        /*
            Porque o resultado de usar fillMaxHeight na Box é de criar uma box
            que ocupa a altura da tela inteira ?

            Segundo a doc
         */
        Box(modifier = Modifier
            .width(8.dp)
            .fillMaxHeight()
            .background(Color.Red)
        )
        Column {
            Text("Hello")
            Text("World")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContentPreview() {
    JustComposeLabsTheme {
        FillingMaxSizeProblem()
    }
}