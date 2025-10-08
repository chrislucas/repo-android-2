package com.br.justcomposelabs.tutorial.animation.compose.expandingtext

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.utils.composable.paddingEdgeToEdge
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

/*
    animation expand and collapse size android view
 */

@Preview(showSystemUi = true)
@Composable
fun TextExpandable() {
    var expanded by remember { mutableStateOf(true) }
    val w = if (expanded) {
        200.dp
    } else {
        100.dp
    }
    JustComposeLabsTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paddingEdgeToEdge()
                .border(2.dp, Color.Red, CutCornerShape(1.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = if (expanded) "Shrink" else "Expand",
                modifier = Modifier
                    .animateContentSize()
                    .padding(20.dp)
                    .border(
                        border = BorderStroke(2.dp, Color.Blue),
                        shape = RoundedCornerShape(20)
                    )
                    .width(w)
                    .clickable {
                        expanded = !expanded
                    }
                ,
                textAlign = TextAlign.Center
            )
        }
    }
}