package com.br.funwithdatabinding.view.features.tutorials.google.uiarchitecture.stateandlifecycle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.funwithdatabinding.view.features.tutorials.google.uiarchitecture.stateandlifecycle.ui.theme.FunWithDataBindingTheme
/*
    Lifecycle of composables 
    https://developer.android.com/develop/ui/compose/lifecycle
    - O ciclo de vida de uma funcao composableé mais simples que das views, activities e fragments
        Quando uma funcao composable necessita  interagir com recursos externos que tem um ciclo
        de vida mais complexo, devemos usar effects (https://developer.android.com/develop/ui/compose/side-effects#state-effect-use-cases)
 */
class StatesAndLifecycleComposableActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    /*
        Se uma
     */
    Column {
        LazyColumn(modifier = modifier) {
            items(4) {
                Text("Text [$it]")
            }
        }

        HorizontalDivider()

        LazyColumn(modifier = modifier) {
            items(3) {
                Text("Another Text [$it]")
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
        Greeting()
    }
}