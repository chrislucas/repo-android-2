package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.RallyScreen
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

internal val TabHeight = 56.dp

@Composable
fun RallyTopAppBar(
    allScreens: List<RallyScreen>,
    onChangeScreen: (RallyScreen) -> Unit,
    currentScreen: RallyScreen,
) {
    Surface(modifier = Modifier.height(TabHeight).fillMaxWidth()) {
        Row(modifier = Modifier.selectableGroup()) {
            allScreens.forEach { screen ->
                RallyTab(
                    text = screen.name,
                    icon = screen.icon,
                    onSelected = { onChangeScreen(screen) },
                    selected = currentScreen == screen,
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RallyTopAppBarPreview() {
    JustComposeLabsTheme {
        Box(
            modifier =
            Modifier
                .systemBarsPadding()
                .statusBarsPadding()
                .fillMaxSize(),
        ) {
            RallyTopAppBar(
                allScreens = RallyScreen.entries.toList(),
                onChangeScreen = {},
                currentScreen = RallyScreen.Overview,
            )
        }
    }
}
