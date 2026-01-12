package com.br.opennonexportedactivity.simpleactiviy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.opennonexportedactivity.simpleactiviy.ui.theme.OpenNonExportedActivityTheme


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

class SimpleNonExportedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OpenNonExportedActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        message = "CallingActivity: $callingActivity | Package: $callingPackage",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .navigationBarsPadding()
                            .systemBarsPadding()
                            .statusBarsPadding()
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(message: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = message
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    OpenNonExportedActivityTheme {
        Greeting(
            message = "Uma activity não exportada, porém acessivel",
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .systemBarsPadding()
                .statusBarsPadding()
        )
    }
}