package com.br.justcomposelabs.tutorial.google.compose.dialog

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

/*
    https://developer.android.com/develop/ui/compose/components/dialog
    - AlertDialog composable prove uma api para criar uma caixa de Dialog
    com o tema Material Design
        - Parametros para criar uma AlertiDialog
            - title
            - text
            - icon
            - onDismissRequest
                - a funcao chamada quando o usuario fecha a caixa de dialogo, quando pressiona
                a tela fora da caixa

            - dismissButton
                - funcao composable para construir o botao de dismiss
            - confirmButton
                - composable para construir o botao de confirmacao
 */

@Composable
fun SimpleAlertDialogComponent(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        modifier = Modifier.systemBarsPadding(),
        icon = {
            Icon(icon, contentDescription = "icon")
        },
        onDismissRequest = onDismissRequest,
        text = {
            Text(dialogText)
        },
        title = {
            Text(dialogTitle)
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("ok")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("calcel")
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun ButtonFire() {
    var showDialog by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(onClick = { showDialog = !showDialog }) {
            Text("Open Dialog")
        }
    }
    if (showDialog) {
        val ctx = LocalContext.current
        SimpleAlertDialogComponent(
            onDismissRequest = { showDialog = false },
            onConfirm = {
                Toast.makeText(ctx, "Ok", Toast.LENGTH_SHORT).show()
                showDialog = false
            },
            dialogTitle = "Confirm Action",
            dialogText = "Are you sure you want to delete this item? This action cannot be undone.",
            icon = Icons.Filled.Info,
        )
    }
}

// ===== PREVIEW FUNCTIONS =====

@Preview(showBackground = true)
@Composable
fun SimpleAlertDialogComponentPreview() {
    val showDialog = remember { mutableStateOf(true) }
    if (showDialog.value) {
        SimpleAlertDialogComponent(
            onDismissRequest = { showDialog.value = false },
            onConfirm = { showDialog.value = false },
            dialogTitle = "Confirm Action",
            dialogText = "Are you sure you want to delete this item? This action cannot be undone.",
            icon = Icons.Filled.Info,
        )
    }
}
