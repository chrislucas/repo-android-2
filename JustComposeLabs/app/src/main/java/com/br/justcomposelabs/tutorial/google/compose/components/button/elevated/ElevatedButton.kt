package com.br.justcomposelabs.tutorial.google.compose.components.button.elevated

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @see com.br.justcomposelabs.tutorial.CircularElevatedButton
 * https://composables.com/jetpack-compose/androidx.compose.material3/material3/components/ElevatedButton/api
 */


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ElevatedButtonComponent() {
    val ctx = LocalContext.current

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        ElevatedButton(
            modifier = Modifier.size(128.dp),
            onClick = {
                Toast.makeText(
                    ctx,
                    "Elevated Button Clicked",
                    Toast.LENGTH_SHORT
                ).show()
            },
            contentPadding = PaddingValues(6.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 20.dp,
                pressedElevation = 2.dp,
                hoveredElevation = 10.dp,
                focusedElevation = 10.dp
            ),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            border = BorderStroke(1.dp, Color(0xFF673AB7)),
            interactionSource = interactionSource
        ) {
            Text(text = "Elevated Button")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ElevatedButtonBrushedComponent() {
    val ctx = LocalContext.current

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        ElevatedButton(
            modifier = Modifier.size(128.dp),
            onClick = {
                Toast.makeText(
                    ctx,
                    "Elevated Button Clicked",
                    Toast.LENGTH_SHORT
                ).show()
            },
            contentPadding = PaddingValues(6.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 20.dp,
                pressedElevation = 2.dp,
                hoveredElevation = 10.dp,
                focusedElevation = 10.dp
            ),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            border = BorderStroke(
                1.dp, Brush.horizontalGradient(
                    listOf(
                        Color(0xFF673AB7),
                        Color(0xFFBE708B)
                    )
                )
            ),
            interactionSource = interactionSource
        ) {
            Text(
                text = "Elevated Button",
                color = if (isPressed) Color(0xFFBE708B) else Color(0xFF673AB7)
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ElevatedButtonTextBrushedComponent() {
    val ctx = LocalContext.current

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val gradient = listOf(
        Color(0xFF673AB7),
        Color(0xFFBE708B)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        ElevatedButton(
            modifier = Modifier.size(128.dp),
            onClick = {
                Toast.makeText(
                    ctx,
                    "Elevated Button Clicked",
                    Toast.LENGTH_SHORT
                ).show()
            },
            contentPadding = PaddingValues(6.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 20.dp,
                pressedElevation = 2.dp,
                hoveredElevation = 10.dp,
                focusedElevation = 10.dp
            ),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            border = BorderStroke(
                1.dp, Brush.horizontalGradient(
                    if (isPressed) gradient.reversed() else gradient
                )
            ),
            interactionSource = interactionSource
        ) {
            Text(
                text = "Elevated Button",
               style = TextStyle(
                   brush = Brush.horizontalGradient(
                       if (isPressed) gradient.reversed() else gradient
                   )
               )
            )
        }
    }
}