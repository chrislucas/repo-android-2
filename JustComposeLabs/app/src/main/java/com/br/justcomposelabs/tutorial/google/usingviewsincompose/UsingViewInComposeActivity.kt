package com.br.justcomposelabs.tutorial.google.usingviewsincompose

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
import androidx.compose.ui.viewinterop.AndroidView
import com.br.canvasviews.Cartesian3DView
import com.br.justcomposelabs.tutorial.google.usingviewsincompose.ui.theme.JustComposeLabsTheme

/*
    https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/views-in-compose
 */
class UsingViewInComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CustomView(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CustomView(modifier: Modifier = Modifier) {

    AndroidView(modifier = modifier, factory = { context ->
        Cartesian3DView(context)
    }, update = {
        /*
            Aqui a view esta sendo inflatada
         */
    })

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JustComposeLabsTheme {
        CustomView(modifier = Modifier.fillMaxSize())
    }
}