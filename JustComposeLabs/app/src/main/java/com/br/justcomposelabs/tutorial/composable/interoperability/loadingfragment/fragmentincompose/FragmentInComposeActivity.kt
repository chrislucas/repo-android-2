package com.br.justcomposelabs.tutorial.composable.interoperability.loadingfragment.fragmentincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.br.justcomposelabs.tutorial.composable.interoperability.loadingfragment.FragmentInCompose
import com.br.justcomposelabs.tutorial.composable.interoperability.loadingfragment.fragmentincompose.ui.theme.JustComposeLabsTheme

class FragmentInComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FragmentInCompose(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

