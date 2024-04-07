package com.br.funwithweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.funwithweather.ui.theme.FunWithWeatherTheme

/**
 * TODO app que permite pesquisar o clima de uma cidade
 *
 * - Criar uma forma de auto completar ao digitar o nome da cidade
 * = consultar numa api os nomes de cidades
 * - Criar um cache dos nomes das cidadaes
 *
 * - criar varios repositorios para consumir os dados de nomes de cidades de api diferentes
 *
 * https://api-ninjas.com/api/city
 * https://countrystatecity.in/
 *
 * api weather all world
 * https://openweathermap.org/api
 *
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FunWithWeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FunWithWeatherTheme {
        Greeting("Android")
    }
}