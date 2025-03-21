package com.br.funwithjetpackcompose.tutorials.google.interoperabillity.usingviewsincompose

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.br.funwithjetpackcompose.tutorials.google.interoperabillity.usingviewsincompose.ui.theme.FunWithDataBindingTheme


/*
    TODO
    https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/views-in-compose?authuser=1
    - para incluir uma view podemos usar a composable function AndroidView
 */
class UsingViewsInComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CustomView(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

class TriangleView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
) : View(context, attr) {

    class Transform {
        var move = 0.0f to 0.0f
        var scale = 1.0f to 1.0f

        operator fun Pair<Float, Float>.plusAssign(newPair: Pair<Float, Float>) {
            move = Pair(move.first + newPair.first, move.second + newPair.second)
        }

        operator fun Pair<Float, Float>.timesAssign(newPair: Pair<Float, Float>) {
            move = Pair(move.first * newPair.first, move.second * newPair.second)
        }

        fun moving(newMove: Pair<Float, Float>) {
            move += newMove
        }

        fun scaling(newMove: Pair<Float, Float>) {
            move *= newMove
        }

        /*
               Metodos para a transformacao de coordenada logicas para de dispositivos
         */

        fun transformX(x: Float) = move.first + scale.first + x

        fun transformY(y: Float) = move.second + scale.second + y
    }

    private val transform = Transform()
    private val path = Path()
    private val paintEdgeTriangleView = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLUE
        strokeWidth = 25.0f
    }

    var selectedItem = 0

    init {
        setBackgroundColor(Color.LTGRAY)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paintEdgeTriangleView)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        transform.scale = if (w > h) {
            // landscape
            h.toFloat() to -h.toFloat()
        } else {
            // portrait
            w.toFloat() to -w.toFloat()
        }
        transform.move = 0.0f to h.toFloat()
        drawingTriangle()
    }

    private fun drawingTriangle() {
        val pA = Pair(transform.transformX(.2f), transform.transformY(.2f))
        val pB = Pair(transform.transformX(.8f), transform.transformY(.8f))
        val pC = Pair(transform.transformX(.8f), transform.transformY(.2f))
        with(path) {
            moveTo(pA.first, pA.second)
            lineTo(pB.first, pB.second)
            lineTo(pC.first, pC.second)
            close()
        }
    }
}

@Composable
fun CustomView(modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableStateOf(0) }
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { ctx ->
            TriangleView(ctx).apply {
                setOnClickListener {
                    selectedItem = 1
                }
            }
        },
        update = { view ->
            view.selectedItem = selectedItem
        }
    )
}

@Composable
fun ShowCustomView(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        CustomView(modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
        ShowCustomView()
    }
}