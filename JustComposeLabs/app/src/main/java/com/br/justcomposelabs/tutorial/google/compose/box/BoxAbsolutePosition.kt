package com.br.justcomposelabs.tutorial.google.compose.box

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun BoxWithOffset() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            "Offset Text",
            // Moves the text 10dp to the right and 20dp down from the top-left corner
            modifier = Modifier.offset(x = 10.dp, y = 20.dp),
        )
    }
}
