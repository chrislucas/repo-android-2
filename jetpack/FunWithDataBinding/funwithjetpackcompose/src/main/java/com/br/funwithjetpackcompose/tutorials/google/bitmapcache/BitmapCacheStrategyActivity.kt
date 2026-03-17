package com.br.funwithjetpackcompose.tutorials.google.bitmapcache

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
import com.br.funwithjetpackcompose.tutorials.google.bitmapcache.ui.theme.FunWithDataBindingTheme

/*
    TODO
    https://developer.android.com/topic/performance/graphics/cache-bitmap
    -
    https://medium.com/@n20/image-caching-in-android-b3bacc34473c
    -

    LRU cache
    https://developer.android.com/reference/android/util/LruCache
    https://medium.com/@lakshyasukhralia/internals-of-lru-cache-in-android-957907aef28f
    https://medium.com/@thatfire.dev/gest%C3%A3o-de-mem%C3%B3ria-no-android-cache-lru-a8f8cd449f71
    https://www.linkedin.com/pulse/mastering-lru-cache-kotlin-akshay-nandwana-zuczf/
 */
class BitmapCacheStrategyActivity : ComponentActivity() {
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