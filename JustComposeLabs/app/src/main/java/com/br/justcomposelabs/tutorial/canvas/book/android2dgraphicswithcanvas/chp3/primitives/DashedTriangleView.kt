package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

class DashedTriangleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paintDashedTriangle = Paint()

    private val path = Path()

    private var scaleStartX: Float by Delegates.notNull()
    private var scaleStartY: Float by Delegates.notNull()
    private var scaleEndX: Float by Delegates.notNull()
    private var scaleEndY: Float by Delegates.notNull()
    private var scaleStrokeWidth: Float by Delegates.notNull()

    private var start: Pair<Float, Float> by Delegates.notNull()
    private var end: Pair<Float, Float> by Delegates.notNull()

    init {
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paintDashedTriangle)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }
}
