package com.br.funwithjetpackcompose.tutorials.medium.paddingasmargin

import android.content.res.Configuration.UI_MODE_TYPE_MASK
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/*
    https://sagarbalyan.medium.com/understanding-margin-and-padding-in-compose-ui-395039a77b76
 */

@Preview(showSystemUi = true, uiMode = UI_MODE_TYPE_MASK)
@Composable
fun TextViewWithMargin() {
    Text(
        text = "Sample Text",
        style = TextStyle(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF64B268),
                    Color(0xFFFFC107),
                    Color(0xFF673AB7),
                    Color(0xFF800080),
                    Color(0xFF2196F3),
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary, CircleShape)
            .padding(16.dp)
    )
}