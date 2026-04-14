package com.br.justcomposelabs.tutorial.google.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme
import com.br.justcomposelabs.utils.composable.LogCompositions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/*
    Interaction state
    https://developer.android.com/develop/ui/compose/touch-input/user-interactions/handling-interactions#interaction-state
 */


@Composable
fun DisplayTextContent(value: String) {
    LogCompositions("DisplayTextContent", "composition")
    Text(value)
}

@Composable
fun UpdateDisplayTextContent(value: String, update: (String) -> Unit) {
    Button(
        onClick = { update(value) },
        shape = CircleShape
    ) {
        Text(value)
    }
}

@Composable
fun UpdateDisplayTextContent(label: String, value: String, update: (String) -> Unit) {
    Button(
        onClick = { update(value) },
        shape = CircleShape
    ) {
        Text(label)
    }
}



@Composable
fun DisplayScreen(contentStart: String) {
    var content by rememberSaveable { mutableStateOf(contentStart) }

    LogCompositions("DisplayScreen", "DisplayScreen")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LogCompositions("DisplayScreenColumn", "DisplayScreenColumn")

        DisplayTextContent(content)

        UpdateDisplayTextContent(value = contentStart) { newValue ->
            content += newValue
        }
        UpdateDisplayTextContent(value = "Reset") { _ ->
            content = contentStart
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayScreenPreview() {
    JustComposeLabsTheme {
        DisplayScreen(contentStart = "+")
    }
}


@Composable
fun ButtonInteractionStateSample() {

}