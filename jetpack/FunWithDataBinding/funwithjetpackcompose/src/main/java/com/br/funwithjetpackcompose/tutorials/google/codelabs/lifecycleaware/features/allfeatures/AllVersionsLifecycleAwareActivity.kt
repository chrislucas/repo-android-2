package com.br.funwithjetpackcompose.tutorials.google.codelabs.lifecycleaware.features.allfeatures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.funwithjetpackcompose.tutorials.google.codelabs.lifecycleaware.features.allfeatures.ui.theme.FunWithDataBindingTheme

class AllVersionsLifecycleAwareActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListFeatures(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ListFeatures( modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Button(onClick = {}) {
            Text("Ola mundo")
        }

        Button(onClick = {}) {
            Text("Ola mundo")
        }

        Button(onClick = {}) {
            Text("Ola mundo")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
        ListFeatures(modifier = Modifier.fillMaxSize())
    }
}