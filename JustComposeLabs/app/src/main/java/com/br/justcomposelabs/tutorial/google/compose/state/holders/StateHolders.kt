package com.br.justcomposelabs.tutorial.google.compose.state.holders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
    TODO
    https://developer.android.com/topic/architecture/ui-layer/stateholders#ui-logic
 */


/*
    The UI state production pipeline
    https://developer.android.com/topic/architecture/ui-layer/stateholders#ui-state-production-pipeline

 */

@Preview(showBackground = true)
@Composable
fun StateCounterByItSelf() {
    /*
        Estado da UI produzido e gerenciado por si mesmo
     */
    var count by remember { mutableIntStateOf(0) }

    Row(modifier = Modifier.fillMaxSize()
        .systemBarsPadding()
        .navigationBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = {++count}) {
            Text(text = "Increment")
        }
        Spacer(modifier = Modifier.padding(2.dp))
        Button(onClick = {--count}) {
            Text(text = "Decrement")
        }
    }
}