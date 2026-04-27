package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.br.justcomposelabs.R
import kotlin.properties.Delegates

/*
    https://share.google/aimode/u057dHXf2Tu9aJzrI
    https://share.google/aimode/FUeZkIOjOdv70kTPf
 */
class RegularPolygonView @JvmOverloads constructor(
    ctx: Context,
    attr: AttributeSet? = null,
    defStyle: Int = 0
) : View(ctx, attr, defStyle) {

    var sides: Int by Delegates.notNull()

    private var join: Paint.Join by Delegates.notNull()

    private var strokeWidthValue: Float by Delegates.notNull()
    private var scaleRadius: Float by Delegates.notNull()

    init {
        context.withStyledAttributes(attr, R.styleable.RegularPolygonView) {
            sides = getInt(R.styleable.RegularPolygonView_sides, 3)

            val joinStyleValue = getInt(
                R.styleable.RegularPolygonView_joinStyle,
                Paint.Join.ROUND.ordinal
            )

            join = Paint.Join.entries[joinStyleValue]

            scaleRadius = getFloat(R.styleable.RegularPolygonView_scaleRadius, .01f)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }
}
