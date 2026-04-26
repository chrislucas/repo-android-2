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
import kotlin.properties.Delegates

/**
 * @param ctx The Context the view is running in, through which it can
 *        access the current theme, resources, etc.
 * @param set The attributes of the XML tag that is inflating the view.
 *
 * @param defStyle  An attribute in the current theme that contains a
 *        reference to a style resource that supplies default values for
 *        the view. Can be 0 to not look for defaults.
 */
class TriangleFilledPathView @JvmOverloads constructor(
    ctx: Context,
    set: AttributeSet? = null,
    defStyle: Int = 0
) : View(ctx, set, defStyle) {

    private val path: Path = Path()

    private val paintFill = Paint(Paint.ANTI_ALIAS_FLAG)

    private val paintStroke = Paint(Paint.ANTI_ALIAS_FLAG)

    private var join: Paint.Join by Delegates.notNull()

    private var strokeWidthValue: Float by Delegates.notNull()

    init {
        context.withStyledAttributes(set, R.styleable.TriangleFilledPathView) {
            val joinValue = getInt(
                R.styleable.TriangleFilledPathView_joinStyle,
                Paint.Join.MITER.ordinal
            )

            join = Paint.Join.entries[joinValue]

            strokeWidthValue = getFloat(
                R.styleable.TriangleFilledPathView_strokeWidthPaint,
                12.0f
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
            strokeWidth = strokeWidthValue
            canvas.drawPath(path, this)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.run {
            moveTo(w * .5f, h * .2f)
            lineTo(w * .8f, h * .8f)
            lineTo(w * .2f, h * .8f)
            close()
        }
    }
}
