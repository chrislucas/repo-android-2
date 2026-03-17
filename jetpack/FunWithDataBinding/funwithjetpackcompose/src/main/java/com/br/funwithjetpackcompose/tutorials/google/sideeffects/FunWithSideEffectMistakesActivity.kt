package com.br.funwithjetpackcompose.tutorials.google.sideeffects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.funwithjetpackcompose.tutorials.google.sideeffects.ui.theme.FunWithDataBindingTheme

/*
    TODO
    Composable functions
    https://developer.android.com/courses/pathways/jetpack-compose-for-android-developers-1

    - funcoes composable nao necessariamente executam na ordem que sao declaradas. Compose sabe
    identificar o que é melhor ser executado primeiro (algo a se estudar omo ele faz isso)

 */
class FunWithSideEffectMistakesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                // Scaffold(modifier = Modifier.fillMaxSize()) { _ -> Test() }
                Test()
            }
        }
    }
}



@Composable
fun ListWithSideEffectBug(words: List<String>) {
    var count = 0
    Row(
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            words.forEach { word ->
                Text("Word: $word")
                count++
            }
        }
    }
    // Text("Words: $count")
}


@Composable
private fun Test() {
    ListWithSideEffectBug("Eu estou ficando maluco com tudo isso".split(" "))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
        Test()
    }
}