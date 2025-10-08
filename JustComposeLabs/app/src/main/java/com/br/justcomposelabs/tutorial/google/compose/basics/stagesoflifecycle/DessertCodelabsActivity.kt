package com.br.justcomposelabs.tutorial.google.compose.basics.stagesoflifecycle

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.lifecycle.Lifecycle
import com.br.justcomposelabs.utils.composable.ComposableLifecycle
import com.br.justcomposelabs.R
import com.br.justcomposelabs.tutorial.google.compose.basics.stagesoflifecycle.data.Datasource
import com.br.justcomposelabs.tutorial.google.compose.basics.stagesoflifecycle.data.Dessert
import com.br.justcomposelabs.tutorial.google.compose.basics.stagesoflifecycle.ui.theme.JustComposeLabsTheme

class DessertCodelabsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                ) { innerPadding ->
                    Layout(
                        modifier = Modifier.padding(innerPadding),
                        desserts = Datasource.desserts
                    )
                }
            }
        }
    }
}


private fun determineDessertToShow(
    desserts: List<Dessert>, dessertsSold: Int
): Dessert {/*
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                break
            }
        }
     */

    return desserts.maxBy { it.startProductionAmount >= dessertsSold }
}


private fun shareSoldDessertsInformation(context: Context, dessertSold: Int, revenue: Int) {
    val intent = Intent().apply {
        this.action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            context.getString(R.string.share_text, dessertSold, revenue)
        )
        type = "text/plain"
    }

    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(
            context,
            context.getString(R.string.sharing_not_available),
            Toast.LENGTH_LONG
        ).show()
    }
}

@Composable
fun Layout(modifier: Modifier = Modifier, desserts: List<Dessert>) {
    ComposableLifecycle { s, e ->
        /*
            what is lifecycle in android
         */
        when (e) {
            Lifecycle.Event.ON_CREATE -> {
                Log.d("LIFECYCLE", "source $s | ON_CREATE")
            }

            Lifecycle.Event.ON_START -> {
                Log.d("LIFECYCLE", "source $s | ON_START")
            }

            Lifecycle.Event.ON_RESUME -> {
                Log.d("LIFECYCLE", "source $s | ON_RESUME")
            }

            Lifecycle.Event.ON_PAUSE -> {
                Log.d("LIFECYCLE", "source $s | ON_PAUSE")
            }

            Lifecycle.Event.ON_STOP -> {
                Log.d("LIFECYCLE", "source $s | ON_STOP")
            }

            Lifecycle.Event.ON_DESTROY -> {
                Log.d("LIFECYCLE", "source $s | ON_DESTROY")
            }

            Lifecycle.Event.ON_ANY -> {
                Log.d("LIFECYCLE", "source $s | ON_ANY")
            }
        }
    }

    var revenue by rememberSaveable { mutableIntStateOf(0) }

    var dessertsSold by rememberSaveable { mutableIntStateOf(0) }

    val currentDessertIndex by rememberSaveable { mutableIntStateOf(0) }

    var currentDessertPrice by rememberSaveable {
        mutableIntStateOf(desserts[currentDessertIndex].price)
    }

    var currentDessertImageId by rememberSaveable {
        mutableIntStateOf(desserts[currentDessertIndex].imageId)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            val context = LocalContext.current
            val direction = LocalLayoutDirection.current

            DessertClickerAppBar(modifier = Modifier.fillMaxWidth()) {
                shareSoldDessertsInformation(context, dessertsSold, revenue)
            }
        }
    ) { paddingValues ->
        DessertClickerScreen(
            modifier = Modifier.padding(paddingValues),
            revenue = revenue,
            dessertsSold = dessertsSold,
            dessertImageId = currentDessertImageId,
            onDessertClicked = {}
        )
    }
}


@Composable
private fun DessertClickerAppBar(
    modifier: Modifier = Modifier,
    onShareButtonClicked: () -> Unit
) {
    Row(modifier = modifier) {

    }
}


@Composable
private fun DessertClickerScreen(
    modifier: Modifier = Modifier,
    revenue: Int,
    dessertsSold: Int,
    @DrawableRes dessertImageId: Int,
    onDessertClicked: () -> Unit,
) {
    Box(modifier = modifier) {
        /*
        Image(
            painter =
        )

         */
    }
}
