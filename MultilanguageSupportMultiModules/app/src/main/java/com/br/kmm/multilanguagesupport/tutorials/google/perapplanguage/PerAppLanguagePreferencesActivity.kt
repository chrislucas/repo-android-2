package com.br.kmm.multilanguagesupport.tutorials.google.perapplanguage

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
import com.br.kmm.multilanguagesupport.tutorials.google.perapplanguage.ui.theme.MultilanguageSupportTheme

/*
    TODO
    https://www.youtube.com/watch?v=DUKnNWwcNvo

    https://android-developers.googleblog.com/2022/11/per-app-language-preferences-part-1.html
    https://android-developers.googleblog.com/2022/12/per-app-language-preferences-part-2.html

    Per-App Language Preferences Sample
    https://github.com/android/user-interface-samples/tree/main/PerAppLanguages/compose_app
 */
class PerAppLanguagePreferencesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultilanguageSupportTheme {
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
    MultilanguageSupportTheme {
        Greeting("Android")
    }
}