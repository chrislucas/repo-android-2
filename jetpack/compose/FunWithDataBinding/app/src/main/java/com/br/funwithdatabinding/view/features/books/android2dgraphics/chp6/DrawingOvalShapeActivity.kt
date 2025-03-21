package com.br.funwithdatabinding.view.features.books.android2dgraphics.chp6

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R
import kotlin.math.max


/*
    Capitulo 6 do livro: Android 2d graphics with Canvas API
 */

class DrawingOvalShapeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_drawing_oval_shape)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}


private class OvalShapeView @JvmOverloads constructor(
    context: Context,
    attrSet: AttributeSet? = null
): View(context, attrSet) {
    /*
        Como desenhar um circulo oval ?
        drawOval(RectF, Paint)

        rectf: Retangulo que define os limites da forma oval. Se
        a largura e o comprimento do retangulo sao iguais (quadrado),
        um circulo é desenhado.
        O retangulo eh representado pelas coordenadas de quatro arestaas:
            - esq, dir, cima e baixo
            - esq e dir o eixo X
            - cima e baixo  oeixo Y
     */

    private val pixelsPaintedSegment = 90.0f
    private val pixelsUnpaintedSegment = 30.0f
    private val intervalOffset = 0.0f
    private val rectF = RectF()
    private var padding = 0.0f

    private val paintOval = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        pathEffect = DashPathEffect(
            floatArrayOf(pixelsPaintedSegment, pixelsUnpaintedSegment), intervalOffset
        )
        color = Color.RED
    }


    private val paintRectF = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.BLUE
    }

    init {
        setBackgroundColor(Color.BLACK)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paintOval.strokeWidth = padding
        paintOval.color = Color.RED
        canvas.drawOval(rectF, paintOval)

        paintRectF.strokeWidth = padding / 12
        canvas.drawRect(rectF, paintRectF)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        padding = max(w, h) * .1f
        rectF.left = padding
        rectF.right = w - padding
        rectF.top = padding
        rectF.bottom = h - padding
    }
}