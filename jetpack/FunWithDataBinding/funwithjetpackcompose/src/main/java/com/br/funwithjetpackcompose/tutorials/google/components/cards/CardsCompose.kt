package com.br.funwithjetpackcompose.tutorials.google.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.br.funwithjetpackcompose.tutorials.google.basics.spacer.basicsmodifier.ui.theme.Purple80
import com.br.funwithjetpackcompose.tutorials.google.basics.spacer.basicsmodifier.ui.theme.PurpleGrey40
import com.br.funwithjetpackcompose.tutorials.google.basics.spacer.basicsmodifier.ui.theme.PurpleGrey80

/*
    https://developer.android.com/develop/ui/compose/components/card
    TODO pintar o background do card com gradiente, verificar a possiblildiade de usar o Brush

    https://stackoverflow.com/questions/69647307/how-to-make-card-gradient-in-jetpack-compose
 */


@Composable
fun FilledCardExample() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(Yellow, Red, Blue)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Filled",
                color = Color.White,
            )
        }
    }
}

@Composable
fun ElevatedCardExample() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier.size(width = 240.dp, height = 100.dp)
    ) {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(Green, Purple80, PurpleGrey80, PurpleGrey40)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Elevated",
                color = Color.Black,
            )
        }
    }
}

@Composable
fun OutlinedCardExample() {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.size(width = 240.dp, height = 100.dp)
    ) {
       Box(
           modifier = Modifier
               .fillMaxSize()
               .background(
                   Brush.horizontalGradient(
                       colors = listOf(Cyan, Yellow)
                   )
               ),
           contentAlignment = Alignment.Center
       ) {
           Text(
               text = "Outlined",
               color = Black
           )
       }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewText() {
    MaterialTheme {
        val padding = Modifier.padding(top = 10.dp, bottom = 10.dp)
        Column {
            FilledCardExample()
            HorizontalDivider(thickness = 2.dp, modifier = padding)
            ElevatedCardExample()
            HorizontalDivider(thickness = 2.dp, modifier = padding)
            OutlinedCardExample()
            HorizontalDivider(thickness = 2.dp, modifier = padding)
        }
    }
}