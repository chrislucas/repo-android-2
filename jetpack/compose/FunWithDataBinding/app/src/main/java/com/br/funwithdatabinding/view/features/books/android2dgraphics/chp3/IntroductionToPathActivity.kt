package com.br.funwithdatabinding.view.features.books.android2dgraphics.chp3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R
import java.util.Locale
import kotlin.math.min

/*
    TODO
 */
class IntroductionToPathActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_introduction_to_path)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

/*
    A classe Path representa uma serie de segmentos de linhas retas, curvas ou outra forma primitiva.
    Um path pode ser aberto ou fechado.

    Criar um Path e desenhar um Path sao duas operacoes distintas.
 */


class CenteredPathView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
) : View(context, attr) {

    private val mPaint: Paint
    private val mPath: Path = Path()

    init {
        setBackgroundColor(Color.BLACK)
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.WHITE
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(mPath, mPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mPaint.strokeWidth = min(w, h) * .1f
        /*

            path.moveTo(x, y)
                - Define o ponto de inicio (corrente) do desenho, sem começar a desenhar. É
                como mover a caneta no ponto x,y
            path.lineTo(x, y)
                - Adiciona um segmento de reta ao "path/caminho" a partir de um
                ponto inicial/corrente (moveTo), depois disso o ponto corrente passa
                a ser o ponto final do segmento de reta
         */
        mPath.run {
            moveTo(w * .2f, h * .2f)
            lineTo(w * .8f, h * .8f)
        }
    }
}


class DrawingCenteredTrianglePath @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
) : View(context, attr) {
    /*
        Join Style
     */
    private val mPaint: Paint
    private val mPaintDrawPoint: Paint
    private val mPath: Path = Path()

    private lateinit var mPoints: Array<Pair<Float, Float>>

    init {
        setBackgroundColor(Color.BLACK)
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.WHITE
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
        }
        mPaintDrawPoint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.WHITE
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            textSize = 80f
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(mPath, mPaint)
        for ((x, y) in mPoints) {
                canvas.drawText(
                    "P(${String.format(Locale.getDefault(), "%.1f, %.1f", x, y)})",
                    x,
                    y - 20F,
                    mPaintDrawPoint
                )
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mPaint.strokeWidth = min(w, h) * .1f
        mPoints = arrayOf(
            Pair(w * .5f, h * .2f),
            Pair(w * .8f, h * .8f),
            Pair(w * .2f, h * .8f),
        )
        mPath.run {
            moveTo(mPoints[0].first, mPoints[0].second)
            for (p in 1 until mPoints.size) {
                lineTo(mPoints[p].first, mPoints[p].second)
            }
            close()
        }
    }
}

class DrawingCenteredSquarePath @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
) : View(context, attr) {

}


class DrawingCenteredPolygonPath @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
) : View(context, attr) {
    /*
        Criar um custom attr para definir quantos lados esse poligono deve ter
     */
}


class BezierCurve @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
) : View(context, attr) {
    /*
        https://proandroiddev.com/drawing-bezier-curve-like-in-google-material-rally-e2b38053038c
     */
}