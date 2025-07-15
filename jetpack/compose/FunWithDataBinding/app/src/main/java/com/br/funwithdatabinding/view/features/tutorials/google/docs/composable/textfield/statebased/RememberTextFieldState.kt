package com.br.funwithdatabinding.view.features.tutorials.google.docs.composable.textfield.statebased


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

/*
    https://developer.android.com/develop/ui/compose/text/migrate-state-based
    Experimental: State-based text fields rely on Material 3 version 1.4.0-alpha14. File any bugs on the issue tracker.
 */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RememberTextFieldState(modifier: Modifier = Modifier) {
    MaterialTheme {
        Surface(modifier = modifier.fillMaxSize()) {
            val textFieldState = rememberTextFieldState()
            BasicTextField(
                state = textFieldState,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier,
                lineLimits = TextFieldLineLimits.SingleLine
            )
        }
    }
}

