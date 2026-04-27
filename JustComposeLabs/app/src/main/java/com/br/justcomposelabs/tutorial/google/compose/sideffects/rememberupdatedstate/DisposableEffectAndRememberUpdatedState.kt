package com.br.justcomposelabs.tutorial.google.compose.sideffects.rememberupdatedstate

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.br.justcomposelabs.BuildConfig
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme
import timber.log.Timber

/*
    DisposableEffect: effects that require cleanup
    https://developer.android.com/develop/ui/compose/side-effects#disposableeffect
 */

@Composable
fun RememberUpdateStateComponent(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onStart: () -> Unit,
    onStop: () -> Unit,
    content: @Composable () -> Unit,
) {
    val currentOnStart by rememberUpdatedState(onStart)
    val currentOnStop by rememberUpdatedState(onStop)

    /*
        DisposableEffect: effects that require cleanup
     */
    DisposableEffect(lifecycleOwner) {
        val tree = Timber.DebugTree()
        if (BuildConfig.DEBUG) {
            Timber.plant(tree) // This sends logs to Logcat
        }

        val observer =
            LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_START -> {
                        currentOnStart()
                        Timber.tag("disposable_effect").d("ON_START=$event")
                    }

                    Lifecycle.Event.ON_STOP -> {
                        currentOnStop()
                        Timber.tag("disposable_effect").d("ON_STOP=$event")
                    }

                    else -> {
                        Timber.tag("disposable_effect").d("Event: $event")
                    }
                }
            }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            Timber.uproot(tree)
        }
    }

    content()
}

@Preview(showSystemUi = true)
@Composable
private fun RememberUpdateStateSamplePreview() {
    JustComposeLabsTheme {
        val ctx = LocalContext.current
        RememberUpdateStateComponent(onStart = {
            Toast
                .makeText(
                    ctx,
                    "ON_START",
                    Toast.LENGTH_LONG,
                ).show()
        }, onStop = {
            Timber.tag("disposable_effect").d("ON_STOP")
        }) {
            Button(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(2.dp),
                onClick = { Toast.makeText(ctx, "Click", Toast.LENGTH_LONG).show() },
                shape = RectangleShape,
            ) {
                Text("Click")
            }
        }
    }
}
