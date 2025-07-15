package com.br.scaffoldttopbarsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.scaffoldttopbarsample.ui.theme.JustComposeLabsTheme


/*
   https://developer.android.com/develop/ui/compose/components/scaffold

    https://developer.android.com/develop/ui/compose/components/app-bars?hl=pt-br
 */
class ScaffoldTopBarSampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SimpleScaffoldTopbar(Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
private fun SimpleScaffoldTopbar(modifier: Modifier = Modifier) {

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JustComposeLabsTheme {
        SimpleScaffoldTopbar()
    }
}