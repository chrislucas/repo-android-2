package com.br.justcomposelabs.tutorial.google.codelabs.navigationcompose.appwalkthrough


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeLayoutScreen(
    currentScreen: CupCakeScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(currentScreen = currentScreen, canNavigateBack = canNavigateBack, navigateUp = navigateUp)
        },
        bottomBar = {
            BottomAppBar {  }
        }
    ) { innerPadding ->
        content(modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    currentScreen: CupCakeScreen,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeAppBarPreview() {
    CupcakeLayoutScreen(
        currentScreen = CupCakeScreen.Start,
        canNavigateBack = false,
        navigateUp = {},
        content = {}
    )
}

@Preview
@Composable
fun TopBarPreview() {
    TopBar(
        currentScreen = CupCakeScreen.Start,
        canNavigateBack = false,
        navigateUp = {}
    )
}