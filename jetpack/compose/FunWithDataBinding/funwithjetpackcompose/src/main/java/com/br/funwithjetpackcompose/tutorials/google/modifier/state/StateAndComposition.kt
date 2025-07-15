package com.br.funwithjetpackcompose.tutorials.google.modifier.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.funwithjetpackcompose.tutorials.canvas.graphics.brush

/*
    https://developer.android.com/develop/ui/compose/state

    Key Term: Composition - a description of the UI built by Compose when it
    executes composables
        - Inital Composition
            creation of a composition by running composable the first time
        - Recomposition
            - Re running composable to update The Composition when Data changes

    State in composable
        - https://developer.android.com/develop/ui/compose/state#state-in-composables
        -  Composable function can use remember API to store objects in memory

        - Um valor compoutado por remember é armazenado na Composition durante o estado inicial
            - Initial Composition
        - O valor armazenado é recuperado durante a recomposição/recomposition
        - remember can store Mutable e Immutable values

        - a funcao mutableStateOf cria um observable MutableState<T> que é um observable
        iintegrado
 */


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ShowContent(contentFirst: String = "hello") {
    val gradientColor = listOf(
        Color(0xff3345aa),
        Color(0xff8899ee),
        Color(0xff3345aa),
        Color(0xff889988)
    )

    val textStyle = TextStyle(brush = Brush.linearGradient(gradientColor))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        /*
            https://developer.android.com/develop/ui/compose/text/style-text
         */
        Text(
            text = contentFirst, modifier = Modifier
                .padding(bottom = 8.dp),
            fontSize = 67.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            style = textStyle
        )
    }
}