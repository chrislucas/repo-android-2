package com.br.funwithjetpackcompose.tutorials.medium.uiprojects.tictactoe

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
import com.br.funwithjetpackcompose.tutorials.medium.uiprojects.tictactoe.ui.theme.FunWithDataBindingTheme

/*
    https://medium.com/@shivamkadam0709/making-a-unbeatable-tic-tac-toe-bot-in-jetpack-compose-36d9a786bfdb

    minimax
    https://www.hackerearth.com/blog/developers/minimax-algorithm-alpha-beta-pruning/
    https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-1-introduction/
    https://www.geeksforgeeks.org/mini-max-algorithm-in-artificial-intelligence/
    https://en.wikipedia.org/wiki/Minimax
 */
class MinimaxTicTacToeActivity : ComponentActivity() {
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