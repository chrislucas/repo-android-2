package com.br.justcomposelabs.tutorial.google.compose.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
    https://www.linkedin.com/posts/marcin-moskala_there-are-three-approaches-how-we-define-share-7475458065685954560-sVhA/?utm_source=share&utm_medium=member_android&rcm=ACoAAAucV48BgdbCBoMmXrArsYNH-OL_jFGhzfk
 */

@Preview(showBackground = true)
@Composable
fun TextFieldLocalStateStraightforward() {
    val state = remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(
                text = "Conteúdo: ${state.value}",
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                style = TextStyle(
                    fontSize = 23.sp
                )
            )
            OutlinedTextField(
                value = state.value,
                onValueChange = { state.value = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldLocalPropertyDelegation() {
    var state by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(
                text = "Conteúdo: $state",
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                style = TextStyle(
                    fontSize = 23.sp
                )
            )
            OutlinedTextField(
                value = state,
                onValueChange = { state = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldLocalPropertyDestructuring() {
    val (value, setter) = remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(
                text = "Conteúdo: $value",
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                style = TextStyle(
                    fontSize = 23.sp
                )
            )
            OutlinedTextField(
                value = value,
                onValueChange = setter
            )
        }
    }
}
