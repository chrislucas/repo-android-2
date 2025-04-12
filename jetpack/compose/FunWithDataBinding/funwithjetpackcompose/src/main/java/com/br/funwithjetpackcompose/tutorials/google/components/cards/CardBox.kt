package com.br.funwithjetpackcompose.tutorials.google.components.cards

import android.text.SpannableStringBuilder
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.mylibrary.R


@Preview(showBackground = true)
@Composable
fun CardBox(modifier: Modifier = Modifier) {
    /*
        Usando Row
            - Row permite adicionar elementos na tela horizontalmente.
            - Column e Row suportam configuracoes para alinhamento de elementos
            contidos em si
     */
    Card(
        colors = CardDefaults.run {
            cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        },
        modifier = modifier.size(width = 240.dp, height = 100.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = stringResource(id = R.string.image_placehholder_description),
                contentScale = ContentScale.Fit,
            )
            Column {
                Text(text = "Hello")
                Text(text = "World")
            }
        }
    }
}