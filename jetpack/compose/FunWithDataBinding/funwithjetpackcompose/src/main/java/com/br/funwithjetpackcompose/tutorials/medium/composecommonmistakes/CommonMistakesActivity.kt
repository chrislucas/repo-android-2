package com.br.funwithjetpackcompose.tutorials.medium.composecommonmistakes

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.funwithjetpackcompose.tutorials.medium.composecommonmistakes.ui.theme.FunWithDataBindingTheme
import com.br.funwithjetpackcompose.tutorials.utils.randomSequenceOfUniqueString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
    TODO
        - modificar essa activity para ser uma lista de botoes para acessar outras
        activities. Cada caso de erro deve gerar 2 activities, uma com o modo errado
        e outra com o modo certo 
    https://medium.com/@riddhi.patel30281/10-common-mistakes-to-avoid-in-jetpack-compose-408276c056ba

    1 - Executar codigo non-compose  em composable function
        - Sempre que um composable é recomposto (recomposed, isto é o estado de um composable
        é modificado), a funcao composable é chamada. Em geral, composable functions podem
        ser chamadas a qualquer momento em qualquer ordem e isso implica em
            - Chamar uma funcao nao-composable (sem a anotacao Compose) dentro de uma funcao composable
            pode fazer com que a non-composable seja executa multiplas vezez

 */
class CommonMistakesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RightWayObserverMutableCollectionChange(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MistakeCases(modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        item {
            Button(onClick = {}) {
                Text("LoadingListWrongWay")
            }
        }

        item {
            Button(onClick = {}) {
                Text("LoadingListRightWay")
            }
        }
    }
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun WrongWayObserverMutableCollectionChange(modifier: Modifier = Modifier) {
    /*
        https://medium.com/@riddhi.patel30281/10-common-mistakes-to-avoid-in-jetpack-compose-408276c056ba
        Da maneira que está a lista nao sera atualiza, uma vez que a recomposicao nao ocorre porque
        Compose nao pode detectar mudancas em tipos mutaveis como MutableList
     */

    val names by remember { mutableStateOf(mutableListOf<String>()) }

    LazyColumn(modifier) {
        item {
            Button(onClick = {
                names.add("+1 nome")
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


fun fetchNames(callback: (List<String>) -> Unit) = CoroutineScope(Dispatchers.IO).launch {
    delay(500L)
    callback(randomSequenceOfUniqueString(10).take(100).toList())
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
        WrongWayObserverMutableCollectionChange()
    }
}