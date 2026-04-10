package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.theme.RallyDialogThemeOverlay


class ButtonConfirmContext(
    val onConfirm: () -> Unit,
    val confirmButtonText: String
)

class ButtonDismissContext(
    val onDismiss: () -> Unit,
    val dismissButtonText: String
)

class RallyAlertDialogContext(
    val buttonConfirmContext: ButtonConfirmContext,
    val buttonDismissContext: ButtonDismissContext,
    val bodyText: String,
    val onTapOutside: () -> Unit
)

@Composable
fun RallyAlertDialog(rallyAlertDialogContext: RallyAlertDialogContext) {
    val buttonConfirmContext = rallyAlertDialogContext.buttonConfirmContext
    val buttonDismissContext = rallyAlertDialogContext.buttonDismissContext
    RallyDialogThemeOverlay {
        AlertDialog(
            modifier = Modifier.systemBarsPadding(),
            onDismissRequest = rallyAlertDialogContext.onTapOutside,
            text = { Text(rallyAlertDialogContext.bodyText) },
            confirmButton = {
                ConfirmDialogButton(
                    buttonConfirmContext.onConfirm,
                    buttonConfirmContext.confirmButtonText
                )
            },
            dismissButton = {
                DismissDialogButton(
                    buttonDismissContext.onDismiss,
                    buttonDismissContext.dismissButtonText
                )
            },
        )
    }
}

@Composable
fun DismissDialogButton(
    onDismiss: () -> Unit,
    buttonText: String
) {
    Column {
        HorizontalDivider(
            Modifier.padding(horizontal = 12.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )
        TextButton(
            onClick = onDismiss,
            shape = RectangleShape,
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(buttonText)
        }
    }
}

@Composable
fun ConfirmDialogButton(
    onConfirm: () -> Unit,
    buttonText: String
) {
    Column {
        HorizontalDivider(
            Modifier.padding(horizontal = 12.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )
        TextButton(
            onClick = onConfirm,
            shape = RectangleShape,
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(buttonText)
        }
    }
}

// ===== PREVIEW FUNCTIONS =====

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RallyAlertDialogPreview() {
    val showDialog = remember { mutableStateOf(true) }
    if (showDialog.value) {
        RallyAlertDialog(
            RallyAlertDialogContext(
                buttonConfirmContext = ButtonConfirmContext(
                    onConfirm = { showDialog.value = false },
                    confirmButtonText = "Confirm"
                ),
                buttonDismissContext = ButtonDismissContext(
                    onDismiss = { showDialog.value = false },
                    dismissButtonText = "Dismiss"
                ),
                bodyText = "Are you sure you want to proceed with this action? This operation cannot be undone.",
                onTapOutside = { showDialog.value = false }
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmDialogButtonPreview() {
    RallyDialogThemeOverlay {
        ConfirmDialogButton(
            onConfirm = {},
            buttonText = "Confirm"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DismissDialogButtonPreview() {
    RallyDialogThemeOverlay {
        DismissDialogButton(
            onDismiss = {},
            buttonText = "Dismiss"
        )
    }
}
