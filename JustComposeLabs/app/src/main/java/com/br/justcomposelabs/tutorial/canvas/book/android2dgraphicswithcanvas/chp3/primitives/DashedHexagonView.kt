package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.br.justcomposelabs.R
import kotlin.math.min
import kotlin.properties.Delegates

class DashedHexagonView
@JvmOverloads
constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(ctx, attrs, defStyleAttr) {
    private val path: Path = Path()

    private val paintFill =
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
        }

    private val paintStroke =
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
        }

    private var sideLength: Float = 0f

    private var join: Paint.Join by Delegates.notNull()

    private var strokeWidthValue: Float by Delegates.notNull()
    private var scaleRadius: Float by Delegates.notNull()

    private var scaleStrokeWidth: Float by Delegates.notNull()

    private var minDimension: Int by Delegates.notNull()

    init {
        context.withStyledAttributes(attrs, R.styleable.DashedHexagonView) {
            scaleRadius =
                getFloat(R.styleable.DashedHexagonView_scaleRadius, 0.01f)
                    .coerceIn(0.01f, 0.1f)

            val joinValueStyle =
                getInt(
                    R.styleable.DashedHexagonView_joinStyle,
                    Paint.Join.ROUND.ordinal,
                )

            join = Paint.Join.entries[joinValueStyle]

            strokeWidthValue =
                getFloat(
                    R.styleable.DashedHexagonView_scaleStrokeWidth,
                    0.0f,
                )

            scaleStrokeWidth =
                getFloat(
                    R.styleable.DashedTriangleView_scaleStrokeWidth,
                    ONE_PERCENT * 3,
                ).coerceIn(ONE_PERCENT, ONE_PERCENT * 10)

            scaleRadius =
                getFloat(
                    R.styleable.DashedHexagonView_scaleRadius,
                    ONE_PERCENT * 90,
                ).coerceIn(ONE_PERCENT * 10, HexagonView.ONE_PERCENT * 90)
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
            color = Color.rgb(0, 0, 0)
            strokeJoin = join
            strokeWidth =
                if (strokeWidthValue > 0.0f) {
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
        sideLength = radius

        // Para garantir que os vértices sejam cobertos, o padrão de dash deve ser
        // sincronizado com o comprimento do lado.
        // Em um hexágono regular, o comprimento de cada lado é igual ao raio.
        val dashOn = sideLength * 0.2f // Por exemplo, o traço ocupa 20% do lado
        val dashOff = sideLength * 0.05f // O espaço ocupa 5% do lado

        // Queremos que o total (dashOn + dashOff) seja um divisor do sideLength
        // para que o padrão se repita exatamente em cada vértice.
        // sideLength / (dashOn + dashOff) deve ser um número inteiro.

        val totalDashLength = dashOn + dashOff
        val segmentsPerSide = (sideLength / totalDashLength).toInt().coerceAtLeast(1)
        val adjustedDashOn = (sideLength / segmentsPerSide) * 0.8f
        val adjustedDashOff = (sideLength / segmentsPerSide) * 0.2f

        // Para ver o Join Style, o traço deve cobrir o vértice.
        // Centralizamos o traço no vértice aplicando um deslocamento (phase)
        // de metade do comprimento do traço.
        val phase = adjustedDashOn / 2f

        paintStroke.pathEffect = DashPathEffect(floatArrayOf(adjustedDashOn, adjustedDashOff), phase)
        path.drawHexagon(cx, cy, radius)
    }

    companion object {
        const val ONE_PERCENT = .01f
    }
}
