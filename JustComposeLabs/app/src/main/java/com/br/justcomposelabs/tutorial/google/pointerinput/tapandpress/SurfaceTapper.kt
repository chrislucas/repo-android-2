package com.br.justcomposelabs.tutorial.google.pointerinput.tapandpress

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

@Preview(showBackground = true)
@Composable
private fun ClickableSurfaceView() {
    /*
        https://github.com/android/snippets/blob/30ed522851a9273c94afcd3a4c30bf674346ad18/compose/snippets/src/main/java/com/example/compose/snippets/touchinput/pointerinput/TapAndPress.kt#L108
     */
    JustComposeLabsTheme {
        Surface {
            Text(
                "Clickable SurfaceView",
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}