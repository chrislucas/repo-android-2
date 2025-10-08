package com.br.justcomposelabs.tutorial.google.compose.modifier.weight

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.glance.text.Text
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

/*
    https://developer.android.com/develop/ui/compose/modifiers#weight-in-row-and-column
 */
@Composable
fun ModifierWeight(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth()
            .border(3.dp, Color.Black)
    ) {
        Text(
            text = "weight 3f",
            modifier = Modifier
                .weight(3f)
                .background(Color.Red)
                .border(2.dp, Color.Red),
            textAlign = TextAlign.Center
        )

        Text(
            text = "weight 1f",
            modifier = Modifier
                .weight(1f)
                .background(Color.Green)
                .border(2.dp, Color.Green),
            textAlign = TextAlign.Center
        )

        Text(
            text = "weight 2f",
            modifier = Modifier
                .weight(2f)
                .background(Color.Blue)
                .border(2.dp, Color.Blue),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierWeightPreview() {
    JustComposeLabsTheme {
        ModifierWeight()
    }
}
