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

/**
 * @param ctx The Context the view is running in, through which it can
 *        access the current theme, resources, etc.
 * @param attrs The attributes of the XML tag that is inflating the view.
 *
 * @param defStyleAttr  An attribute in the current theme that contains a
 *        reference to a style resource that supplies default values for
 *        the view. Can be 0 to not look for defaults.
 */
class HexagonView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(ctx, attrs, defStyleAttr) {
    private val path: Path = Path()

    private val paintFill = Paint(Paint.ANTI_ALIAS_FLAG)

    private val paintStroke = Paint(Paint.ANTI_ALIAS_FLAG)

    private var join: Paint.Join by Delegates.notNull()

    private var strokeWidthValue: Float by Delegates.notNull()
    private var scaleRadius: Float by Delegates.notNull()
    private var scaleStrokeWidth: Float by Delegates.notNull()

    private var minDimension: Int by Delegates.notNull()

    init {
        context.withStyledAttributes(attrs, R.styleable.HexagonView) {
            val joinStyleValue =
                getInt(
                    R.styleable.HexagonView_joinStyle,
                    Paint.Join.ROUND.ordinal,
                )

            join = Paint.Join.entries[joinStyleValue]
            strokeWidthValue = getFloat(R.styleable.HexagonView_strokeWidthPaint, 0.0f)
            scaleStrokeWidth = getFloat(
                R.styleable.HexagonView_scaleStrokeWidth,
                ONE_PERCENT * 3,
            ).coerceIn(
                ONE_PERCENT,
                ONE_PERCENT * 10,
            )

            scaleRadius = getFloat(
                R.styleable.HexagonView_scaleRadius,
                ONE_PERCENT * 90,
            ).coerceIn(ONE_PERCENT * 10, ONE_PERCENT * 90)
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
        /**
         * Para desenhar um rehagono regular, o metodo mais preciso é calcular os seis vertices
         * usando trigonometria básica
         */
        val cx = w * ONE_PERCENT * 50
        val cy = h * ONE_PERCENT * 50
        minDimension = min(w, h)
        val radius = minDimension * ONE_PERCENT * 50 * scaleRadius
        path.drawHexagon(cx, cy, radius, 0.0f)
    }

    companion object {
        private const val ONE_PERCENT = .01f
    }
}
