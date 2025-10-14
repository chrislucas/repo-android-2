package com.br.justcomposelabs.tutorial.google.crashlytics.forcecrash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics

/*
    https://firebase.google.com/docs/crashlytics/get-started?platform=android
 */


@Preview(showBackground = true, showSystemUi = true, name = "ButtonForceCrash")
@Composable
fun ButtonForceCrash() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { throw IllegalStateException("Test Crash") }) {
            Text("Test Crashlytics")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, name = "ButtonThrowNoFatalException")
@Composable
fun ButtonThrowNoFatalException() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        /*
            Relatar exceções não fatais
            https://firebase.google.com/docs/crashlytics/customize-crash-reports?authuser=1&hl=pt-br&platform=android
         */
        Button(onClick = {
            try {
                tryExec()
            } catch (e: Exception) {
                Firebase.crashlytics.recordException(e)
            }
        }
        ) {
            Text("Test Crashlytics")
        }
    }
}

private fun tryExec() {
    throw IllegalStateException("No Fatal Exception Crash")
}