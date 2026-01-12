package com.br.justcomposelabs.tutorial.customview.loadoverlaycustomview.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.LifecycleResumeEffect
import com.br.justcomposelabs.tutorial.customview.loadoverlaycustomview.WrapperLoadView
import com.br.justcomposelabs.tutorial.customview.loadoverlaycustomview.ui.ui.theme.JustComposeLabsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WrapperLoadViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContent {
            JustComposeLabsTheme (darkTheme = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoadView(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LoadView(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }

    // Obtém o rootView apenas se não estiver no Preview
    val currentView = LocalView.current
    val rootViewGroup = remember(currentView) {
        if (!currentView.isInEditMode) {
            currentView.rootView as? ViewGroup
        } else {
            null
        }
    }

    // Cria o WrapperLoadView apenas uma vez e gerencia seu ciclo de vida
    val wrapperLoadView = remember(rootViewGroup) {
        rootViewGroup?.let { WrapperLoadView(it) }
    }

    // Libera recursos quando o composable sai da composição
    DisposableEffect(wrapperLoadView) {
        onDispose {
            wrapperLoadView?.release()
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                scope.launch {
                    isLoading = true
                    wrapperLoadView?.show()
                    delay(3000L)
                    wrapperLoadView?.hide()
                    isLoading = false
                }
            },
            enabled = !isLoading
        ) {
            Text("Show Overlay")
        }
    }

    /*
        Run code on lifecycle events
        https://developer.android.com/topic/libraries/architecture/compose#run-code
     */

    LifecycleResumeEffect(wrapperLoadView) {
        /*
            https://developer.android.com/topic/libraries/architecture/compose#lifecycleresumeeffect
         */

        onPauseOrDispose {
            wrapperLoadView?.release()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    JustComposeLabsTheme {
        LoadView()
    }
}