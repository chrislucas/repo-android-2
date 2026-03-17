package com.br.composerecomposition.tutorials.google.graphicmodifiers

import android.graphics.Shader
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.composerecomposition.R
import com.br.composerecomposition.ui.theme.ComposeRecompositionTheme

/*
    https://developer.android.com/develop/ui/compose/graphics/draw/modifiers
 */


@Composable
fun ContentObscured(Content: @Composable () -> Unit) {

    var pointerOffset by remember { mutableStateOf(Offset(0f, 0f)) }

    Column {
        Content()
    }
}


@Preview(showBackground = true)
@Composable
fun ContentObscuredPreview() {
    ComposeRecompositionTheme() {
        ContentObscured {
            val imageBrush = ShaderBrush(
                ImageShader(
                    ImageBitmap.imageResource(id = R.drawable.dog),
                    tileModeX = TileMode.Mirror,
                    tileModeY = TileMode.Mirror,
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(imageBrush),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Box Content",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = TextStyle(
                        fontSize = 50.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}