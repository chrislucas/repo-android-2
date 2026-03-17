package com.br.funwithjetpackcompose.tutorials.google.basics.spanstyle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.funwithjetpackcompose.tutorials.google.basics.spanstyle.ui.theme.FunWithDataBindingTheme


/*
    TODO
    https://developer.android.com/develop/ui/compose/text/style-text#multiple-styles
 */
class MultipleStylesSpanActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TextMultiplesStyles(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TextMultiplesStyles(modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(text = "Hello")
    }
}

@Preview(showBackground = true)
@Composable
fun TextMultiplesStylesPreview() {
    FunWithDataBindingTheme {
        TextMultiplesStyles()
    }
}