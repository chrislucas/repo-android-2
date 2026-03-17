package com.br.funwithjetpackcompose.tutorials.google.span.spnnabletext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.funwithjetpackcompose.tutorials.google.span.HelloWorld
import com.br.funwithjetpackcompose.tutorials.google.span.SampleTextSpan
import com.br.funwithjetpackcompose.tutorials.google.span.spnnabletext.ui.theme.FunWithDataBindingTheme

/*
    https://medium.com/@shmehdi01/spannable-text-in-jetpack-compose-a6ec33a20ec2

    Spannable Text in Jetpack Compose — Android
    https://medium.com/codeandroid/spannable-text-in-jetpack-compose-android-a93b9306678d
 */
class SpannableTextInComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SampleTextSpan(listOf("chris", "lucas"), modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}



@Composable
fun ExamplesSpanStyle() {
    Column {
        Box {
            SampleTextSpan(listOf("chris", "lucas", "ferandes"))
        }

        Box {
            HelloWorld()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
        ExamplesSpanStyle()
    }
}