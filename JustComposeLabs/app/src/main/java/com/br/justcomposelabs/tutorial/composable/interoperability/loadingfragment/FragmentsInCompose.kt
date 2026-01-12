package com.br.justcomposelabs.tutorial.composable.interoperability.loadingfragment

import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.compose.AndroidFragment
import com.br.justcomposelabs.tutorial.composable.interoperability.InteroperabilityComposeFragment
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme


/*
    https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/views-in-compose#fragments-in-compose
 */


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FragmentInCompose(modifier: Modifier = Modifier) {
    AndroidFragment<InteroperabilityComposeFragment>(modifier)
}

/*
    Example Google AI
 */


@Composable
fun FragmentScreen(
    modifier: Modifier = Modifier,
    clazz: Class<out Fragment>,
    arguments: Bundle,
    callback: (Fragment) -> Unit
) {
    Column(modifier = modifier) {
        Text("Above Content Fragment")

        AndroidFragment(
            clazz = clazz,
            arguments = arguments
        ) { fragment ->
            callback(fragment)
        }

        Text("Below Content Fragment")

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FragmentScreenExample() {
    JustComposeLabsTheme {
        FragmentScreen(
            modifier = Modifier
                .statusBarsPadding()
                .systemBarsPadding(),
            clazz = InteroperabilityComposeFragment::class.java,
            arguments = Bundle.EMPTY
        ) { fragment ->

        }
    }
}
