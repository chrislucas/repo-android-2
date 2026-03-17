package com.br.funwithjetpackcompose.tutorials.feature.searchpckagesapps

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
import com.br.funwithjetpackcompose.tutorials.feature.searchpckagesapps.ui.theme.FunWithDataBindingTheme


/*
    TODO
        - Uma tela com uma caixa de texto para filtrar por package
        - criar um auto completar bonitinho
            - Tentar usar uma Trie para o auto complete

    Fonte
    https://www.linkedin.com/posts/cavin-macwan_%F0%9D%97%A0%F0%9D%97%AE%F0%9D%98%80%F0%9D%98%81%F0%9D%97%B2%F0%9D%97%BF%F0%9D%97%B6%F0%9D%97%BB%F0%9D%97%B4-%F0%9D%97%94%F0%9D%97%BB%F0%9D%97%B1%F0%9D%97%BF%F0%9D%97%BC%F0%9D%97%B6%F0%9D%97%B1-%F0%9D%97%A3%F0%9D%97%AE-activity-7309827621557563392-wbjW/?utm_source=share&utm_medium=member_android&rcm=ACoAAAucV48BgdbCBoMmXrArsYNH-OL_jFGhzfk
    https://stackoverflow.com/questions/76210209/how-to-list-all-android-apps-packages
    https://jetpackcomposesnippets.meticha.com/snippet-details/things-you-can-do-with-androids-package-manager-api-a7val1
 */
class FilterAllAppsActivity : ComponentActivity() {
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