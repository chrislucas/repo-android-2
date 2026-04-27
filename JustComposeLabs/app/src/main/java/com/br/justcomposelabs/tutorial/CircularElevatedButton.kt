package com.br.justcomposelabs.tutorial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

@Composable
fun CircularElevatedButton(
    content: @Composable () -> Unit,
    onClick: () -> Unit = {},
) {
    ElevatedButton(
        onClick,
        shape = CircleShape,
        modifier = Modifier.size(128.dp),
        contentPadding = PaddingValues(6.dp),
        elevation =
        ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 20.dp,
            pressedElevation = 2.dp,
            hoveredElevation = 10.dp,
            focusedElevation = 10.dp,
        ),
        colors =
        ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
    ) {
        content()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CircularElevatedButtonPreview() {
    JustComposeLabsTheme {
        Row(
            modifier =
            Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .statusBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            CircularElevatedButton(content = {
                Icon(Icons.Default.Add, contentDescription = "Add")
            })
        }
    }
}
