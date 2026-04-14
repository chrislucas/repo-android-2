package com.br.justcomposelabs.tutorial.medium.compose

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
    https://www.linkedin.com/posts/marcin-moskala_cool-counter-made-with-animatedcontent-with-activity-7443220673268916224-HD6z/?utm_source=share&utm_medium=member_android&rcm=ACoAAAucV48BgdbCBoMmXrArsYNH-OL_jFGhzfk
 */

@Preview(showBackground = true)
@Composable
fun AnimatedCounterComponent() {
    var counter by remember { mutableIntStateOf(0) }
    val offset = 200

    val slideRight = slideIn(initialOffset = { IntOffset(offset, 0) }) + scaleIn() togetherWith
        slideOut(targetOffset = { IntOffset(-offset, 0) }) + scaleOut()

    val slideLeft = slideIn(initialOffset = { IntOffset(-offset, 0) }) + scaleIn() togetherWith
        slideOut(targetOffset = { IntOffset(offset, 0) }) + scaleOut()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            .navigationBarsPadding()
            .systemBarsPadding()
    ) {
        AnimatedContent(
            targetState = counter,
            transitionSpec = {
                if (targetState > initialState) {
                    slideRight
                } else {
                    slideLeft
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) { value ->
            Text(
                text = "$value",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic

                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = { counter++ },
            shape = RectangleShape,
            modifier = Modifier
                .padding(top = 2.dp, bottom = 2.dp)
                .size(size = 60.dp)
        ) {
            Text(
                "+1",
                style = TextStyle(
                    fontSize = 32.sp
                )
            )
        }

        Button(
            onClick = { counter-- },
            shape = RectangleShape,
            modifier = Modifier
                .padding(top = 2.dp, bottom = 2.dp)
                .size(size = 60.dp)

        ) {
            Text(
                "-1",
                style = TextStyle(
                    fontSize = 32.sp
                )
            )
        }
    }
}
