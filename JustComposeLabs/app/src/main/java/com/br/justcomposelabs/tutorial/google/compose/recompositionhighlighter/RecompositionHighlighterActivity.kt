package com.br.justcomposelabs.tutorial.google.compose.recompositionhighlighter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.tutorial.google.compose.recompositionhighlighter.ui.theme.JustComposeLabsTheme
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Objects
import kotlin.math.min


/*
    https://github.com/android/snippets/blob/main/compose/recomposehighlighter/src/main/java/com/example/android/compose/recomposehighlighter/MainActivity.kt

 */
class RecompositionHighlighterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterRecomposition(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CounterRecomposition(modifier: Modifier = Modifier) {
    var counter by remember { mutableIntStateOf(0) }
    var counterRecomposition by remember { mutableLongStateOf(0) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { counter++ }) {
            Text("Click to recompose")
        }

        Text(
            "Count Click: ${counter}, Counter Recompoition: $counterRecomposition",
            modifier = Modifier.recomposeHighlighter { counter ->
                counterRecomposition = counter
            }
        )
    }
}

/*
    https://github.com/android/snippets/blob/main/compose/recomposehighlighter/src/main/java/com/example/android/compose/recomposehighlighter/RecomposeHighlighter.kt

    Um Modificador que desenha uma borda ao redor de um elemento que sofre recomposicao. Essa
    borda aumenta de tamanho e interpola do vermelho para o verde conform mais recomposiciooes
    ocorrem ate um tempo limite


     TODO estudar Custom Annotation

 */


@Stable
fun Modifier.recomposeHighlighter(callback: (Long) -> Unit = {}): Modifier = this.then(RecomposeHighlighterElement(callback))

private class RecomposeHighlighterElement(private val callback: (Long) -> Unit = {}) : ModifierNodeElement<RecomposeHighlighterModifier>() {


    /*

     */
    override fun InspectorInfo.inspectableProperties() {
        debugInspectorInfo { name = "recomposeHighlighter" }
    }

    override fun create(): RecomposeHighlighterModifier = RecomposeHighlighterModifier()

    override fun update(node: RecomposeHighlighterModifier) {
        node.incrementCompositions()
        callback(node.valueTotalComposition.longValue)
    }

    // It's never equal, so that every recomposition triggers the update function.
    override fun equals(other: Any?): Boolean = false

    override fun hashCode(): Int = Objects.hash(this)
}


private class RecomposeHighlighterModifier : Modifier.Node(), DrawModifierNode {

    private var timerJob: Job? = null

    override val shouldAutoInvalidate: Boolean = false

    /*
        Numero de de composicoes que ocorreram
     */
    private var totalCompositions: Long = 0
        set(value) {
            if (field == value) return
            if (value > 0)
                restartTimer()

            field = value
            invalidateDraw()
        }

    val valueTotalComposition = mutableLongStateOf(totalCompositions)

    fun incrementCompositions() {
        totalCompositions++
        valueTotalComposition.longValue = totalCompositions
    }

    override fun onAttach() {
        super.onAttach()
        restartTimer()
        Timber.d("RecompositionHighlighter - onAttach")
    }

    override fun onReset() {
        super.onReset()
        totalCompositions = 0
        valueTotalComposition.longValue = totalCompositions
        timerJob?.cancel()
        Timber.d("RecompositionHighlighter - onReset count reset")
    }

    override fun onDetach() {
        super.onDetach()
        timerJob?.cancel()
        Timber.d("RecompositionHighlighter - onDetach")
    }

    private fun restartTimer() {
        if (!isAttached)
            return

        timerJob?.cancel()
        timerJob = coroutineScope.launch {
            delay(3000)
            totalCompositions = 0
            valueTotalComposition.longValue = totalCompositions
            Timber.d("RecompositionHighlighter - restartTimer")
        }
    }

    override fun ContentDrawScope.draw() {
        // desenha o conteudo atual
        /**
         * @see ContentDrawScope.drawContent
         */
        drawContent()

        /*
            Abaixo o codigo responsavbel por desenhar o highlight, se necessario.
            Muito do codigo abaixo foi tirado do Modifier.border
         */

        val hasValidBorderParams = size.minDimension > 0f
        if (!hasValidBorderParams || totalCompositions <= 0) {
            return
        }

        val (color: Color, strokeWidthPx: Float) = when (totalCompositions) {
            1L -> Color.Blue to 1f.dp.toPx()
            2L -> Color.Green to 2f.dp.toPx()
            else -> {

                val start = Color.Yellow.copy(alpha = 0.8f)
                val stop = Color.Red.copy(alpha = 0.5f)
                val fraction = min(1f, (totalCompositions - 1).toFloat() / 100f)

                val c = lerp(
                    start = start,
                    stop = stop,
                    fraction = fraction
                )

                Timber.d("RecompositionHighlighter - color $c")

                c to totalCompositions.toInt().dp.toPx()
            }
        }

        val halfStroke = strokeWidthPx / 2
        val topLeft = Offset(halfStroke, halfStroke)
        val borderSize = Size(size.width - strokeWidthPx, size.height - strokeWidthPx)

        val fillArea = (strokeWidthPx * 2) > size.minDimension
        val rectTopLeft = if (fillArea) Offset.Zero else topLeft
        val size = if (fillArea) size else borderSize
        val style = if (fillArea) Fill else Stroke(strokeWidthPx)

        drawRect(
            brush = SolidColor(color),
            topLeft = rectTopLeft,
            size = size,
            style = style
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JustComposeLabsTheme {
        CounterRecomposition()
    }
}