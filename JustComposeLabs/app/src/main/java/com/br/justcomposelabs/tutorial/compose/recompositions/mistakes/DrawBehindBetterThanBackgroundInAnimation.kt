package com.br.justcomposelabs.tutorial.compose.recompositions.mistakes

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
    https://www.linkedin.com/feed/update/urn:li:activity:7447659533109497856/
    A autora do post afirma que:
        - drawBehind lê o estado na fase de draw, pulando recomposicoes. Em animacoes a 60fps isso faz diferença para
        animações mais suavizadas.
 */

class SimpleViewModel : ViewModel() {
    private val first = Color(0xff332334)
    private val second = Color(0xFF3F51B5)

    var color: Color by mutableStateOf(second)

    fun startColorAnimation() {
        viewModelScope.launch {
            while (true) {
                color = if (color == first) second else first
                delay(1000L)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UnstableBackgroundInAnimation(viewModel: SimpleViewModel = viewModel()) {
    val color by animateColorAsState(
        targetValue = viewModel.color,
        animationSpec = tween(durationMillis = 1000),
    )

    LaunchedEffect(Unit) {
        // Do something with the color
        viewModel.startColorAnimation()
    }

    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(color),
        contentAlignment = Alignment.Center,
    ) {
        /**
         * @see com.br.justcomposelabs.tutorial.google.compose.text.style.TextDecoratedUnderline
         * e demais métodos
         */
        Text(
            text = "UnstableBackgroundInAnimation",
            style =
            TextStyle(
                color = Color.White,
                fontSize = 23.sp,
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline,
                fontSynthesis = FontSynthesis.All,
            ),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StableBackgroundInAnimation(viewModel: SimpleViewModel = viewModel()) {
    val color by animateColorAsState(
        targetValue = viewModel.color,
        animationSpec = tween(durationMillis = 1000),
    )

    LaunchedEffect(Unit) {
        // Do something with the color
        viewModel.startColorAnimation()
    }

    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .drawBehind {
                drawRect(color)
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "StableBackgroundInAnimation",
            style =
            TextStyle(
                color = Color.White,
                fontSize = 23.sp,
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline,
                fontSynthesis = FontSynthesis.All,
            ),
            textAlign = TextAlign.Center,
        )
    }
}
