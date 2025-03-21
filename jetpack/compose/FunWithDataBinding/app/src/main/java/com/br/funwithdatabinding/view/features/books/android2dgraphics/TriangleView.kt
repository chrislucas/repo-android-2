package com.br.funwithdatabinding.view.features.books.android2dgraphics

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/*
    Misconception about Kotlin @JvmOverloads for Android View Creation
    https://proandroiddev.com/misconception-about-kotlin-jvmoverloads-for-android-view-creation-cb88f432e1fe

    - Resumo, as vezes o overload passando defStyleAttr = 0 como default pode causar problemas, pois
    nem todas as views passam o valor zero quando 0 como default no construtor
        (Context, AttributeSet)
        - exemplo
          public TextInputEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
            this(context, attrs, R.attr.editTextStyle);
          }
        - TextInputEditText passa R.attr.editTextStyle, se passarmos 0 como default,
        vamos sobreescrever esse valor da classe R e podemos ter um resultado inesperado

        - Caso criemos uma SubClasse de uma View que tenha esse comportamento, o ideial
        eh fazer sobrecarga somente do construtor (Context, AttributeSet)


    Do not always trust @JvmOverloads
    https://medium.com/@mmlodawski/https-medium-com-mmlodawski-do-not-always-trust-jvmoverloads-5251f1ad2cfe

    A deep dive into Android View constructors

 */
class TriangleView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
) : View(context, attr) {

    private val transform = Transform()
    private val path = Path()
    private val paintEdgeTriangleView = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLUE
        strokeWidth = 25.0f
    }

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
        /*
            TODO entender como esses pontos formam um triangulo na tela
         */
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