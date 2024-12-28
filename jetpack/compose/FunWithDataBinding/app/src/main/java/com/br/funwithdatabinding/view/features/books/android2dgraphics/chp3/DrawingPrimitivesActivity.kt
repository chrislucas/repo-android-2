package com.br.funwithdatabinding.view.features.books.android2dgraphics.chp3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Cap
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R
import kotlin.math.min

/*
    chp3 - Introduction to Drawing Primitives
 */
class DrawingPrimitivesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_drawing_primitives)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}


class LineView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
) : View(context, attr) {

    /*
        As cores sao representadas por inteiros. Em java
        inteiros tem 4 bytes e cada byte armazena um componente da cor
        argb, do byte mais signinificante para o menos significante
     */

    private val paint = Paint().apply {
        color = Color.argb(200, 255, 255, 255) //Color.WHITE
    }

    private var width: Int = 0;
    private var height: Int = 0;

    init {
        setBackgroundColor(Color.argb(50, 200, 0, 0))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawLine(
            width * TWENTY_PERCENT,
            height * TWENTY_PERCENT,
            width * EIGHTY_PERCENT,
            height * EIGHTY_PERCENT,
            paint
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w
        height = h
        paint.strokeWidth = min(width, height) * TEN_PERCENT
        paint.strokeCap = Cap.ROUND
    }


    companion object {
        const val TWENTY_PERCENT = .2f
        const val TEN_PERCENT = .1f
        const val EIGHTY_PERCENT = .8f
    }
}