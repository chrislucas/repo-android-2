package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

@Composable
internal fun AlertItem(message: String) {
    Row(
        modifier =
        Modifier.Companion
            .padding(RallyDefaultPadding)
            // Regard the whole row as one semantics node. This way each row will receive focus as
            // a whole and the focus bounds will be around the whole row content. The semantics
            // properties of the descendants will be merged. If we'd use clearAndSetSemantics instead,
            // we'd have to define the semantics properties explicitly.
            .semantics(mergeDescendants = true) {},
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.Companion.weight(1f),
            text = message,
        )
        IconButton(
            onClick = {},
            modifier =
            Modifier.Companion
                .align(Alignment.Companion.Top)
                .clearAndSetSemantics {},
        ) {
            Icon(Icons.AutoMirrored.Filled.Sort, contentDescription = null)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlertItemPreview() {
    JustComposeLabsTheme {
        Box(
            modifier =
            Modifier
                .systemBarsPadding()
                .statusBarsPadding()
                .fillMaxSize(),
        ) {
            AlertItem(
                message = "Heads up, you've used up 90% of your Shopping budget for this month.",
            )
        }
    }
}
