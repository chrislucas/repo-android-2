package com.br.funwithviewmodel.tutorials.google.compose.libraries.activityresult

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.rememberAsyncImagePainter

/*
    https://developer.android.com/develop/ui/compose/libraries#activity_result
 */


@Preview(showBackground = true, name = "GetContentComponentPreview", showSystemUi = true)
@Composable
fun GetContentComponent(modifier: Modifier = Modifier) {
    /*
        https://github.com/android/snippets/blob/2f9a4c3dac384ba8231186a4883ec86273127d4e/compose/snippets/src/main/java/com/example/compose/snippets/interop/ComposeWithOtherLibraries.kt#L80
     */

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        // Handle the returned Uri
        imageUri = uri
    }

    Column(
        modifier = modifier.fillMaxWidth()
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {
        Button(onClick = { launcher.launch("image/*") }) {
            Text(text = "Load Image")
        }
        Image(
            painter = rememberAsyncImagePainter(imageUri),
            contentDescription = "My Image"
        )
    }
}