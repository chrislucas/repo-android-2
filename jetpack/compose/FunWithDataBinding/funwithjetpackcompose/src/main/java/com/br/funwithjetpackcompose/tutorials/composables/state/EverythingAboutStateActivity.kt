package com.br.funwithjetpackcompose.tutorials.composables.state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.br.funwithjetpackcompose.tutorials.composables.state.ui.theme.FunWithDataBindingTheme

class EverythingAboutStateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExperimentClickableTextStateRemember(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

