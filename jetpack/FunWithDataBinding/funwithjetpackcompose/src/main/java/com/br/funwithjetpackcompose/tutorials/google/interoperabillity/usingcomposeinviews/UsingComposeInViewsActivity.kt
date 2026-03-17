package com.br.funwithjetpackcompose.tutorials.google.interoperabillity.usingcomposeinviews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import com.br.funwithjetpackcompose.tutorials.google.interoperabillity.callingandroidframework.broadcastreceiver.Greeting
import com.br.funwithjetpackcompose.tutorials.google.interoperabillity.callingandroidframework.broadcastreceiver.ui.theme.FunWithDataBindingTheme
import com.br.mylibrary.databinding.ActivityUsingComposeInViewsBinding

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

    private val binding : ActivityUsingComposeInViewsBinding by lazy {
        ActivityUsingComposeInViewsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        enableEdgeToEdge()
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    Greeting()
                }
            }
        }
    }
}


@Composable
fun Greeting() {
    ListNumbers()
}

@Composable
private fun ListNumbers() {
    LazyColumn {
        items((0..100).toList()) {
            Text("Texto $it")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        ListNumbers()
    }
}