package com.br.opennonexportedactivity

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
import com.br.opennonexportedactivity.ui.theme.OpenNonExportedActivityTheme


/*
   https://developer.android.com/reference/android/app/Activity#getCallingPackage()
   Return the name of the package that invoked this activity.

   Note: prior to Build.VERSION_CODES.JELLY_BEAN_MR2,
   the result from this method was unstable. If the process hosting
   the calling package was no longer running, it would return null instead of the
   proper package name. You can use getCallingActivity() and retrieve
   the package name from that instead.

   https://developer.android.com/reference/android/app/Activity#getCallingActivity()

   Return the name of the activity that invoked this activity.

   Note: if the calling activity is not expecting a result,
   hen the calling package will be null.

*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OpenNonExportedActivityTheme {
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
    OpenNonExportedActivityTheme {
        Greeting("Android")
    }
}