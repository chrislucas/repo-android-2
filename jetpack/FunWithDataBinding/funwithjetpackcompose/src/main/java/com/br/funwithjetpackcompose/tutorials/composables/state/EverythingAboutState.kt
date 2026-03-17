package com.br.funwithjetpackcompose.tutorials.composables.state

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

/*
    https://composables.com/jetpack-compose-tutorials/state
 */


@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Everything you need to know about State"
)
@Composable
fun ExperimentClickableTextStateRemember(modifier: Modifier = Modifier) {
    val state = remember { mutableStateOf(false) }

    Surface(modifier = modifier) {
        Text(buildAnnotatedString {
            withLink(
                LinkAnnotation.Clickable(tag = "Clickable") {
                    state.value = !state.value
                }
            ) {
                withStyle(
                    style = SpanStyle(
                        color = Color(0XFF4843D3),
                        background = Color.Yellow,
                        fontSize = TextUnit(32.0f, TextUnitType.Sp)
                    )
                ) {
                    append(if (state.value) "True" else "False")
                }
            }
        })
    }
}