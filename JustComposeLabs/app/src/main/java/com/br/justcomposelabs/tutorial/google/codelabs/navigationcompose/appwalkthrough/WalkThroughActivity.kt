package com.br.justcomposelabs.tutorial.google.codelabs.navigationcompose.appwalkthrough

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.br.justcomposelabs.tutorial.google.codelabs.navigationcompose.appwalkthrough.ui.OrderViewModel
import com.br.justcomposelabs.tutorial.google.codelabs.navigationcompose.appwalkthrough.ui.theme.JustComposeLabsTheme

/*
    TODO
    https://developer.android.com/codelabs/basic-android-kotlin-compose-navigation#2
    https://github.com/google-developer-training/basic-android-kotlin-compose-training-cupcake
    https://github.com/google-developer-training/

    https://developer.android.com/develop/ui/compose/navigation
 */


class WalkThroughActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CupCakeContainerUI(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CupCakeContainerUI(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // get the name of the current screen
    val currentScreen = CupCakeScreen.valueOf(
        backStackEntry?.destination?.route ?: CupCakeScreen.Start.name
    )

    CupcakeAppScaffold(
        currentScreen,
        canNavigateBack = navController.previousBackStackEntry != null,
        navigateUp = { navController.navigateUp() },
        modifier = modifier
    ) { innerModifier ->

        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = CupCakeScreen.Start.name,
            modifier = innerModifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.TopStart,
        )  {
            composable (route = CupCakeScreen.Start.name) {

            }

            composable (route = CupCakeScreen.Flavor.name) {

            }

            composable (route = CupCakeScreen.Pickup.name) {

            }

            composable (route = CupCakeScreen.Summary.name) {

            }
        }
    }
}


private fun cancelOrderAndNavigateToStart() {

}

@Composable
fun NavController() {

}









