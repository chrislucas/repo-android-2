package com.br.funwithdatabinding.view.features.books.android2dgraphics.chp6

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
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
    Capitulo 6 do livro: Android 2d graphics with Canvas API
 */

class CirclesOvalsAndArcsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_circles_ovals_and_arcs)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}


private class CircleShapeView @JvmOverloads constructor(
    context: Context,
    attrSet: AttributeSet? = null
) : View(context, attrSet) {
    /*
        metodo para desenhar um circulo usando canvas
        drawCircle(cx, cy, radius, paint)

        cx, cy -> coordenada do centro do ciruclo
        radius: raio
        paint: Paint object
     */

    val pixelsPaintedSegment = 90.0f
    val pixelsUnpaintedSegment = 30.0f
    val intervalOffset = 0.0f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        pathEffect = DashPathEffect(
            floatArrayOf(pixelsPaintedSegment, pixelsUnpaintedSegment), intervalOffset
        )
        color = Color.RED
    }

    data class CircleDescription(
        var centerX: Float = 0.0f,
        var centerY: Float = 0.0f,
        var mRadius: Float = 0.0f
    ) {
        fun set(cx: Float, cy: Float, r: Float) {
            centerX = cx
            centerY = cy
            mRadius = r
        }
    }

    private var circleDescription = CircleDescription()

    init {
        setBackgroundColor(Color.BLACK)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val (cx, cy, radius) = circleDescription
        paint.strokeWidth = radius  * .1f
        // paint.strokeCap = Paint.Cap.SQUARE
        canvas.drawCircle(cx, cy, radius, paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        circleDescription.set(w / 2f, h / 2f, min(w, h) * .7f * .5f)
    }
}