package com.br.justcomposelabs.tutorial.ktacademy.modifierorder

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/*
    https://kt.academy/article/modifier-order
 */


@Preview(showBackground = true)
@Composable
fun ModifierOrderMatters() {
    Column {
        Text(
            text = "Hello World!", modifier =
                Modifier.background(Color(0xFFD78BC8))
                    .padding(20.dp)
        )

        Text(
            text = "Hello World!", modifier =
                Modifier
                    .padding(20.dp)
                    .background(Color(0xFFD78BC8))

        )


        Text(
            text = "Hello World!", modifier =
                Modifier
                    .clickable {}
                    .padding(20.dp)
                    .background(Color(0xFFEEDEEA))

        )

        Text(
            text = "Hello World!", modifier =
                Modifier
                    .padding(20.dp)
                    .clickable {}
                    .background(Color(0xFFEEDEEA)),

        )
    }
}