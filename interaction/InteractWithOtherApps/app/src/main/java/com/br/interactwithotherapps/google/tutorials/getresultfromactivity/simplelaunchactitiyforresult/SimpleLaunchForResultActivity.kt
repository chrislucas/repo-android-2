package com.br.interactwithotherapps.google.tutorials.getresultfromactivity.simplelaunchactitiyforresult

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.interactwithotherapps.google.tutorials.getresultfromactivity.simplelaunchactitiyforresult.ui.theme.InteractWithOtherAppsTheme

/*
    Launch an activity for result
    https://developer.android.com/training/basics/intents/result
 */
class SimpleLaunchForResultActivity : ComponentActivity() {

    val getContent = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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
        Log.i("", "CallingActivity: $callingActivity | Package: $callingPackage")

        setContent {
            InteractWithOtherAppsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ButtonLaunch(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        getContent.launch("image/*")
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonLaunch(modifier: Modifier = Modifier, action: () -> Unit) {
    Box(modifier = modifier) {
        Button(onClick = action) {
            Text("Get Content Image")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InteractWithOtherAppsTheme {
        ButtonLaunch(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .navigationBarsPadding(),
            action = {}
        )
    }
}