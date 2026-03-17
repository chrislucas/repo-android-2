package com.br.funwithjetpackcompose.tutorials.medium.composecommonmistakes

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlin.collections.plus

@Preview(showBackground = true)
@Composable
fun RightWayObserverMutableCollectionChange(modifier: Modifier = Modifier) {

    /*
        https://medium.com/@riddhi.patel30281/10-common-mistakes-to-avoid-in-jetpack-compose-408276c056ba
        2. Using mutablelist as a state.
            - Sugestao: Usar um State de uma colecao imutavel. Sempre
            que um estado é completamente modificado e trocado por um novo valor
            Compose detecta essa mudança e aplica o recompose a todos os
            composables que usam esse estado

     */

    var names by remember { mutableStateOf(listOf<String>()) }

    LazyColumn(modifier) {
        item {
            Button(onClick = {
                names + "+${names.size + 1} nome"
                Log.i("LIST_NAME", "$names")
            }) {
                Text(text = "Adicionar nome")
            }
        }

        items(names) {
            Text(text = it)
        }
    }
}