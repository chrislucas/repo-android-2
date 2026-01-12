package com.br.justcomposelabs.tutorial.composable.interoperability.loadingfragment.fragmentscreencompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.tutorial.composable.interoperability.InteroperabilityComposeFragment
import com.br.justcomposelabs.tutorial.composable.interoperability.loadingfragment.FragmentScreen
import com.br.justcomposelabs.tutorial.composable.interoperability.loadingfragment.fragmentscreencompose.ui.theme.JustComposeLabsTheme

class FragmentScreenComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FragmentScreen(
                        modifier = Modifier.padding(innerPadding),
                        clazz = InteroperabilityComposeFragment::class.java,
                        arguments = Bundle.EMPTY
                    ) { fragment ->

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun FragmentScreenExample() {

}