package com.br.justcomposelabs.tutorial.canvas.geom.triangle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.br.justcomposelabs.tutorial.canvas.geom.triangle.ui.theme.JustComposeLabsTheme

class DraggableTriangleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SpringAnimationDraggableTriangleComponent(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun SpringAnimationDraggableTriangleComponent(modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            SpringAnimationDraggableTriangleView(context)
        },
        update = { it.invalidate() }
    )
}

@Preview(showBackground = true)
@Composable
fun DraggableTriangleViewPreview() {
    JustComposeLabsTheme {
        SpringAnimationDraggableTriangleComponent(Modifier.fillMaxWidth())
    }
}
