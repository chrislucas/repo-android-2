package com.br.funwithjetpackcompose.tutorials.google.performance.managermemory

import android.content.ComponentCallbacks2
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
import com.br.funwithjetpackcompose.tutorials.google.performance.managermemory.ui.theme.FunWithDataBindingTheme

/*
    TODO
    Overview of memory management
    https://developer.android.com/topic/performance/memory-overview

    https://developer.android.com/topic/performance/memory?authuser=1

    ComponentCallbacks2
    https://developer.android.com/reference/android/content/ComponentCallbacks2

    Listening to Memory events using onTrimMemory() on Android
    https://medium.com/@gurpreetsk/memory-management-on-android-using-ontrimmemory-f500d364bc1a

    65% Smaller APKs and 70% Less Memory: How I Optimized My Android App-Part I (APK Size)
    https://medium.com/@tarunanchala/65-smaller-apks-and-70-less-memory-how-i-optimized-my-android-app-part-i-apk-size-146a970649a8
 */
class ManagerMemoryActivity : ComponentActivity(), ComponentCallbacks2 {
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

    override fun onTrimMemory(level: Int) {
        super<ComponentActivity>.onTrimMemory(level)
        if (level >= ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            // Release memory related to UI elements, such as bitmap caches.
        }

        if (level >= ComponentCallbacks2.TRIM_MEMORY_BACKGROUND) {
            // Release memory related to background processing, such as by
            // closing a database connection.
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