package com.br.funwithjetpackcompose.tutorials.google.interoperabillity.usingcomposeinviews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.br.funwithjetpackcompose.tutorials.google.interoperabillity.callingandroidframework.broadcastreceiver.Greeting
import com.br.funwithjetpackcompose.tutorials.google.interoperabillity.callingandroidframework.broadcastreceiver.ui.theme.FunWithDataBindingTheme

class UsingComposeInViewsActivity : ComponentActivity() {
    /*
        todo
        https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/compose-in-views

        composition jetpack compose
            - Lifecycle of composables
                - https://developer.android.com/develop/ui/compose/lifecycle
        --------------------------------------------------------------------------------------------
            - Jetpack Compose phases
                - https://developer.android.com/develop/ui/compose/phases
         -------------------------------------------------------------------------------------------
             - Exploring Composition and Recomposition in Android Jetpack Compose Series #03
                - https://medium.com/@myofficework000/exploring-composition-and-recomposition-in-android-jetpack-compose-series-03-504b70f22f0a
         ----------------------------------------------------------------------------------------------


     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {

            }
        }
    }
}


@Composable
fun Greeting() {

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {

    }
}