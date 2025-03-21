package com.br.funwithjetpackcompose.tutorials.google.pointers.scrolling

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
import com.br.funwithjetpackcompose.tutorials.google.pointers.scrolling.ui.theme.FunWithDataBindingTheme


/*
    https://developer.android.com/develop/ui/compose/touch-input/pointer-input/scroll?hl=pt-br
 */
class ScrollableComposeModifierActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScrollableView(Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun ScrollableView(modifier: Modifier = Modifier) {

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
    }
}