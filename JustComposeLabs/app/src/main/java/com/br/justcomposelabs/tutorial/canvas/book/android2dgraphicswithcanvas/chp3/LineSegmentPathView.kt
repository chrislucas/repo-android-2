package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Cap
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.br.justcomposelabs.R
import kotlin.math.min
import kotlin.properties.Delegates
import androidx.core.graphics.toColorInt


/*
    Path: Classe que represent segmentos de linha, curvas ou outros objetos geometricos primitivos.


 */

class LineSegmentPathView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(ctx, attrs, defStyleAttr) {


    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = "#FF003432".toColorInt()
        style = Paint.Style.STROKE
        strokeCap = Cap.ROUND
    }


    private val line = Path()

    private var scaleStartX: Float by Delegates.notNull()
    private var scaleStartY: Float by Delegates.notNull()
    private var scaleEndX: Float by Delegates.notNull()
    private var scaleEndY: Float by Delegates.notNull()
    private var scaleStrokeWidth: Float by Delegates.notNull()

    private var start: Pair<Float, Float> by Delegates.notNull()
    private var end: Pair<Float, Float> by Delegates.notNull()


    init {
        context.withStyledAttributes(attrs, R.styleable.LineSegmentPathView) {
            scaleStartX = getFloat(R.styleable.LineView_scaleStartX, .2f)
            scaleStartY = getFloat(R.styleable.LineView_scaleStartY, .2f)
            scaleEndX = getFloat(R.styleable.LineView_scaleEndX, .8f)
            scaleEndY = getFloat(R.styleable.LineView_scaleEndY, .8f)

            start = getDimension(R.styleable.LineView_startX, 400.0f) * scaleStartX to
                getDimension(R.styleable.LineView_startY, 400.0f) * scaleStartY

            end = getDimension(R.styleable.LineView_endX, 600.0f) * scaleEndX to
                getDimension(R.styleable.LineView_endY, 2400.0f) * scaleEndY

            scaleStrokeWidth = getFloat(R.styleable.LineView_scaleStrokeWidth, 0.05f)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(line, linePaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        linePaint.strokeWidth = min(w, h) * scaleStrokeWidth // Set stroke width to 1% of the smaller dimension
        start = w * scaleStartX to h * scaleStartY
        end = w * scaleEndX to h * scaleEndY
        val (startX, startY) = start
        val (endX, endY) = end
        line.run {
            reset()
            moveTo(startX, startY)
            lineTo(endX, endY)
            /*
                se usar o métod0 close o Android usa o strokeJoin,
                unindo o comeco do path ao final

                Recomendação: onSizeChanged vs onDraw
                    Para o caso específico da classe LineSegmentPathView,
                    onde o caminho (Path) depende apenas das dimensões da View (usando escalas como scaleStartX,
                    scaleStartY, etc.), o melhor lugar para executar moveTo e lineTo é no onSizeChanged.
                    Aqui estão os motivos detalhados:

                    1. Performance e Eficiência

                    • onSizeChanged: É chamado apenas quando o tamanho da View muda (ex: rotação de tela,
                     redimensionamento de layout). Como os cálculos de coordenadas dependem de w (largura) e h (altura),
                     faz sentido processá-los apenas uma vez e reutilizar o objeto Path pronto.

                    • onDraw: É chamado toda vez que a View precisa ser invalidada (animações, toques, mudanças de estado).

                    Executar reset(), moveTo() e lineTo() aqui força o Android a reconstruir
                    a geometria do Path em cada frame (geralmente 60 ou 120 vezes por segundo),
                    o que é um desperdício de CPU/GPU se os dados não mudaram.


                    2. Quando usar o onDraw?

                    Você deve mover essa lógica para o onDraw apenas se as coordenadas do
                    Path mudarem dinamicamente de forma frequente, como, por exemplo:

                    • Numa animação onde os pontos se movem frame a frame.
                    • Se os pontos dependerem de interação do usuário em tempo real
                    (ex: seguir o dedo no onTouchEvent).
             */
        }


    }
}