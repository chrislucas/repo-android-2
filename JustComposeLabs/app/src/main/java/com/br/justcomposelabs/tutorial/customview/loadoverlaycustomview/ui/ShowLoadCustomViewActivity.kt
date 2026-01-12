package com.br.justcomposelabs.tutorial.customview.loadoverlaycustomview.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.br.justcomposelabs.R
import com.br.justcomposelabs.tutorial.customview.loadoverlaycustomview.LoadCustomViewOverlay
import com.br.justcomposelabs.tutorial.customview.loadoverlaycustomview.ui.ui.theme.JustComposeLabsTheme

class ShowLoadCustomViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // setDefaultNightMode(MODE_NIGHT_NO)
        setContent {
            JustComposeLabsTheme (darkTheme = false){
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OpaqueOverlay(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun OpaqueOverlay(modifier: Modifier = Modifier) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val ctx = LocalContext.current
    val customViewRef: LoadCustomViewOverlay = remember {
        LoadCustomViewOverlay(ctx).also {
            it.registerLifecycleOwner(lifecycleOwner)
        }
    }
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        // Camada 1 (fundo): LoadCustomViewOverlay - preenche toda a tela
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { _ -> customViewRef }
        )

        // Camada 2 (frente): Botão um pouco abaixo do centro
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    scope.launch {
                        isLoading = true
                        customViewRef.show()
                        delay(3000L) // 3 segundos
                        customViewRef.hide()
                        isLoading = false
                    }
                },
                enabled = !isLoading
            ) {
                Text("Show Overlay")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JustComposeLabsTheme {
        OpaqueOverlay()
    }
}