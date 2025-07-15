package com.br.kmm.multilanguagesupport.tutorials.google.managingstate

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
    https://developer.android.com/develop/ui/compose/state#retrigger-remember
 */

@Composable
fun FormContent(modifier: Modifier = Modifier) {
    /*
        https://www.youtube.com/watch?v=mymWGMy9pYI
     */
    Column(modifier = modifier.padding(16.dp)) {

        var contentText by remember { mutableStateOf("") }

        Text(
            text = "Form Content, $contentText!",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.headlineLarge
        )

        OutlinedTextField(
            value = contentText,
            onValueChange = {
                contentText = it
            },
            label = { Text("Label") })
    }
}

@Preview(
    showSystemUi = true, showBackground = true, name = "Form Content", uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun PreviewFormContent() {
    Surface {
        FormContent(Modifier.fillMaxSize())
    }
}