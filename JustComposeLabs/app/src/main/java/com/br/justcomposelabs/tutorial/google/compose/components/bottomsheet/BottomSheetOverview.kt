package com.br.justcomposelabs.tutorial.google.compose.components.bottomsheet

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.allfeatures.ui.theme.JustComposeLabsTheme
import kotlinx.coroutines.launch

/*
    https://developer.android.com/develop/ui/compose/components/bottom-sheets
    https://m3.material.io/components/bottom-sheets/accessibility

    tutoriais
    https://proandroiddev.com/improving-the-modal-bottom-sheet-api-in-jetpack-compose-5ca56901ada8

    Bottom Sheets in Jetpack Compose: ModalBottomSheet vs. BottomSheetScaffold
    https://medium.com/@ramadan123sayed/bottom-sheets-in-jetpack-compose-modalbottomsheet-vs-bottomsheetscaffold-24751326e0ec

    How to use Bottom Sheets with Material 2 and 3 with examples in Jetpack Compose
    https://composables.com/blog/bottomsheet
 */



@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(true) }
    JustComposeLabsTheme {
        Scaffold(floatingActionButton = {}) { paddingValues ->
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState,
                    modifier = Modifier.padding(paddingValues)
                ) {
                    Button(onClick = {
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {
                        Text(text = "Hide bottom sheet")
                    }
                }
            }
        }
    }
}