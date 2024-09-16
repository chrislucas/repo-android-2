package com.br.navfeaturescompose.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.navfeatures.utils.startActivityByDeeplink
import com.br.navfeaturescompose.views.ui.theme.FunWithDataBindingTheme

/**
 * Criar um componente de lista usando jetpackcompose para listar
 * as telas desse projeto  que podem ser alcançadas via deeplink
 * Usar o metodo
 * @see startActivityByDeeplink
 * em cada item da lista para poder redirecionar para a a activity correta
 */
class FeaturesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
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
    FunWithDataBindingTheme {
        Greeting("Android")
    }
}