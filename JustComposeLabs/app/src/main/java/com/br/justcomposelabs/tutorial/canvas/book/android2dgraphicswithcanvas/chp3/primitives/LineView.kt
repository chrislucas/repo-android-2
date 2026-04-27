package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Cap
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.br.justcomposelabs.R
import com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.drawTextOnScreenMiddle
import kotlin.math.min
import kotlin.properties.Delegates

class LineView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val linePaint =
        Paint().apply {
            color = Color.RED
            style = Paint.Style.STROKE
            isAntiAlias = true
            strokeCap = Cap.ROUND
        }

    private var textPaint =
        Paint().apply {
            color = Color.BLUE
            textSize = 50f
            isAntiAlias = true
        }

    private val textDimensionPaint =
        Paint().apply {
            color = Color.BLACK
            textSize = 50f
            isAntiAlias = true
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }

    private var start: Pair<Float, Float> by Delegates.notNull()
    private var end: Pair<Float, Float> by Delegates.notNull()

    private var scaleStrokeWidth: Float by Delegates.notNull()
    private var scaleStartX: Float by Delegates.notNull()
    private var scaleStartY: Float by Delegates.notNull()
    private var scaleEndX: Float by Delegates.notNull()
    private var scaleEndY: Float by Delegates.notNull()

    init {
        context.withStyledAttributes(attrs, R.styleable.LineView) {
            start = getDimension(R.styleable.LineView_startX, 0.0f) to
                getDimension(R.styleable.LineView_startY, 0.0f)
            end = getDimension(R.styleable.LineView_endX, 200.0f) to
                getDimension(R.styleable.LineView_endY, 200.0f)

            scaleStrokeWidth = getFloat(R.styleable.LineView_scaleStrokeWidth, 0.02f)
            scaleStartX = getFloat(R.styleable.LineView_scaleStartX, .2f)
            scaleStartY = getFloat(R.styleable.LineView_scaleStartY, .2f)
            scaleEndX = getFloat(R.styleable.LineView_scaleEndX, .8f)
            scaleEndY = getFloat(R.styleable.LineView_scaleEndY, .8f)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val (startX, startY) = start
        val (endX, endY) = end
        canvas.drawLine(startX, startY, endX, endY, linePaint)
        drawTextFromStartToEnd(startX, startY, endX, endY, canvas)
        canvas.drawTextOnScreenMiddle("($width X $height)", textDimensionPaint)
    }

    private fun drawTextFromStartToEnd(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
        canvas: Canvas,
    ) {
        canvas.drawText(
            "Start: (${startX.toInt()}, ${startY.toInt()})",
            startX + 50f,
            startY + 40f,
            textPaint,
        )

        canvas.drawText(
            "End: (${endX.toInt()}, ${endY.toInt()})",
            endX - 450f,
            endY - 10f,
            textPaint,
        )
    }

    override fun onSizeChanged(
        w: Int,
        h: Int,
        oldw: Int,
        oldh: Int,
    ) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Update the end point to be the bottom right corner of the view
        // end = w.toFloat() to h.toFloat()
        linePaint.strokeWidth = min(w, h) * scaleStrokeWidth // Set stroke width to 1% of the smaller dimension
        start = w * scaleStartX to h * scaleStartY
        end = w * scaleEndX to h * scaleEndY
    }
}
