package com.br.justcomposelabs.tutorial.google.compose.textfields.editable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.tutorial.CenteredBoxSlotComponent
import com.br.justcomposelabs.tutorial.composable.functions.activities.ui.theme.JustComposeLabsTheme

/*
    https://share.google/aimode/tu2FvEeHpHNHpnFxk
 */

@Composable
fun EditableTextFieldTrailingIcon(
    label: String,
    initValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf(initValue) }
    var isEditable by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }
    OutlinedTextField(
        value = text,
        placeholder = { Text("Enter $label") },
        onValueChange = {
            if (isEditable) {
                text = it
                onValueChange(it)
            }
        },
        /*
            Usar readOnly para desabilitar o campo permite manter a legibilidade do componente.
            Ao Usar enabled o TextField fica com a cor de desabilitado, um tom de cinz.
         */
        readOnly = !isEditable,
        label = { Text(label) },
        modifier = modifier.focusRequester(focusRequester),
        // enabled = isEditable,
        trailingIcon = {
            IconButton(onClick = {
                if (isEditable) {
                    onValueChange(text)
                } else {
                    focusRequester.requestFocus()
                }
                isEditable = !isEditable
            }) {
                Icon(
                    imageVector = if (isEditable) Icons.Default.Check else Icons.Default.Edit,
                    contentDescription = if (isEditable) "Check" else "Edit"
                )
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewEditableTextFieldTrailingIcon() {
    JustComposeLabsTheme {
        CenteredBoxSlotComponent(modifier = Modifier.fillMaxSize()) {
            EditableTextFieldTrailingIcon(
                modifier = Modifier
                    .systemBarsPadding()
                    .navigationBarsPadding(),
                label = "Name",
                initValue = "",
                onValueChange = {}
            )
        }
    }
}

@Composable
fun EditableTextFieldTrailingTextButton(
    label: String,
    initValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf(initValue) }
    var isEditable by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    OutlinedTextField(
        value = text,
        onValueChange = {
            if (isEditable) {
                text = it
                onValueChange(it)
            }
        },
        placeholder = {
            Text("Enter $label")
        },
        readOnly = !isEditable,
        label = { Text(label) },
        modifier = modifier.focusRequester(focusRequester),
        trailingIcon = {
            TextButton(
                onClick = {
                    if (isEditable) {
                        // Handle save logic here
                        onValueChange(text)
                    } else {
                        focusRequester.requestFocus()
                    }
                    isEditable = !isEditable
                },
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
            ) {
                /*
                    https://share.google/aimode/hbLrut6SBINBma492
                 */
                AnimatedContent(
                    targetState = isEditable,
                    transitionSpec = {
                        if (targetState) {
                            slideInVertically { height -> height } + fadeIn() togetherWith
                                slideOutVertically { height -> -height } + fadeOut()
                        } else {
                            slideInVertically { height -> -height } + fadeIn() togetherWith
                                slideOutVertically { height -> height } + fadeOut()
                        }
                    },
                    label = "EditableTextFieldTrailingTextButton"
                ) { editable ->
                    Text(if (editable) "Save" else "Edit")
                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewEditableTextFieldTrailingTextButton() {
    JustComposeLabsTheme {
        CenteredBoxSlotComponent(modifier = Modifier.fillMaxSize()) {
            EditableTextFieldTrailingTextButton(
                modifier = Modifier
                    .systemBarsPadding()
                    .navigationBarsPadding(),
                label = "Name",
                initValue = "",
                onValueChange = {}
            )
        }
    }
}
