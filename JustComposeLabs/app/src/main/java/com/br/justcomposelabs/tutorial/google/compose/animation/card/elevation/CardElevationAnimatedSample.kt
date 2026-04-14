package com.br.justcomposelabs.tutorial.google.compose.animation.card.elevation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @param isElevated When true, the animated target elevation is higher (see [animateDpAsState]).
 * @param onIsElevatedChange Called with the new elevated flag when the user taps the card (toggle).
 */
@Composable
fun CardElevationAnimated(
    isElevated: Boolean,
    onIsElevatedChange: (Boolean) -> Unit,
) {
    val elevation by animateDpAsState(
        targetValue = if (isElevated) 32.dp else 2.dp,
        label = "CardElevationAnimation"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .border(border = BorderStroke(1.dp, Color.Black))
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Card(
            onClick = { onIsElevatedChange(!isElevated) },
            elevation = CardDefaults.cardElevation(defaultElevation = elevation),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
            modifier = Modifier
                .size(width = 240.dp, height = 100.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Card content!",
                    style = TextStyle(
                        fontSize = 23.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CardElevationAnimatedPreview() {
    var isElevated by remember { mutableStateOf(false) }
    CardElevationAnimated(
        isElevated = isElevated,
        onIsElevatedChange = { isElevated = it },
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OutlinedCardExample() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .width(width = 240.dp)
            // .height(IntrinsicSize.Min)
        ) {
            Text(
                text = "Outlined",
                modifier = Modifier
                    .padding(6.dp),
                textAlign = TextAlign.Center,
            )

            Text(
                text = "Outlined",
                modifier = Modifier
                    .padding(6.dp),
                textAlign = TextAlign.Center,
            )

            Text(
                text = "Outlined",
                modifier = Modifier
                    .padding(6.dp),
                textAlign = TextAlign.Center,
            )

            Text(
                text = "Outlined",
                modifier = Modifier
                    .padding(6.dp),
                textAlign = TextAlign.Center,
            )

            Text(
                text = "Outlined",
                modifier = Modifier
                    .padding(6.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}
