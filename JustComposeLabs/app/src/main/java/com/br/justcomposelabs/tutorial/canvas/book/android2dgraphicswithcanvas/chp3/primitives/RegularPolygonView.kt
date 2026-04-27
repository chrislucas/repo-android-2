package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.br.justcomposelabs.R
import kotlin.math.min
import kotlin.properties.Delegates

/*
    https://share.google/aimode/u057dHXf2Tu9aJzrI
    https://share.google/aimode/FUeZkIOjOdv70kTPf
 */
class RegularPolygonView
@JvmOverloads
constructor(
    ctx: Context,
    attr: AttributeSet? = null,
    defStyle: Int = 0,
) : View(ctx, attr, defStyle) {
    var sides: Int by Delegates.notNull()

    private var join: Paint.Join by Delegates.notNull()

    private var strokeWidthValue: Float by Delegates.notNull()
    private var scaleRadius: Float by Delegates.notNull()
    private var scaleStrokeWidth: Float by Delegates.notNull()

    private var rotationDegree: Float by Delegates.notNull()

    private var minDimension: Int by Delegates.notNull()

    private val path = Path()

    private val paintFill = Paint(Paint.ANTI_ALIAS_FLAG)

    private val paintStroke = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        context.withStyledAttributes(attr, R.styleable.RegularPolygonView) {
            sides = getInt(R.styleable.RegularPolygonView_sides, 3)
            val joinStyleValue =
                getInt(
                    R.styleable.RegularPolygonView_joinStyle,
                    Paint.Join.ROUND.ordinal,
                )

            join = Paint.Join.entries[joinStyleValue]

            scaleRadius = getFloat(
                R.styleable.RegularPolygonView_scaleRadius,
                ONE_PERCENT * 90,
            ).coerceIn(
                ONE_PERCENT * 10,
                ONE_PERCENT * 90
            )

            strokeWidthValue = getFloat(
                R.styleable.RegularPolygonView_strokeWidthPaint,
                0.0f
            )

            scaleStrokeWidth = getFloat(
                R.styleable.RegularPolygonView_scaleStrokeWidth,
                ONE_PERCENT * 3,
            ).coerceIn(
                ONE_PERCENT,
                ONE_PERCENT * 10,
            )

            rotationDegree = getFloat(
                R.styleable.RegularPolygonView_rotationDegree,
                Math.PI.toFloat(),
            ).coerceIn(
                (-Math.PI).toFloat(),
                Math.PI.toFloat(),
            )
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paintFill.run {
            style = Paint.Style.FILL
            color = Color.rgb(255, 153, 123)
            canvas.drawPath(path, this)
        }

        paintStroke.run {
            style = Paint.Style.STROKE
            color = Color.rgb(0, 0, 0)
            strokeJoin = join
            strokeWidth = if (strokeWidthValue > 0.0f) {
                strokeWidthValue
            } else {
                minDimension * scaleStrokeWidth
            }
            canvas.drawPath(path, this)
        }
    }

    override fun onSizeChanged(
        w: Int,
        h: Int,
        oldw: Int,
        oldh: Int,
    ) {
        super.onSizeChanged(w, h, oldw, oldh)
        val cx = w * ONE_PERCENT * 50
        val cy = h * ONE_PERCENT * 50
        minDimension = min(w, h)
        val radius = minDimension * ONE_PERCENT * 50 * scaleRadius
        path.drawRegularPolygon(cx, cy, radius, sides, rotationDegree)
    }

    companion object {
        private const val ONE_PERCENT = .01f
    }
}
