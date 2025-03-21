package com.br.funwithdatabinding.view.features.books.android2dgraphics.playwithpaths

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.AttributeSet
import android.util.FloatProperty
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R
import timber.log.Timber
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/*
    TODO
    https://medium.com/androiddevelopers/playing-with-paths-3fbc679a6f77
 */
class PlayingWithPathActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_playing_with_path)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}


/*
    https://gist.github.com/nickbutcher/b41da75b8b1fc115171af86c63796c5b#file-polygonlapsdrawable-kt
 */


private class PolygonCanvas(
    private val width: Int,
    private val height: Int
) {
    /*
        Area pra desenhar o poligono
     */
    private val cx = width / 2
    private val cy = height / 2
    private val pathMeasure = PathMeasure()

    fun describePolygon(sides: Int, radius: Float): Path {
        /*
            Coordenadas polares sao uteis para desenhar poligonos, podemos
            calcular o angulo necessario para produzir as arestas e usar multiplos
            desses angulos e o raio para descrever cada ponto.

            Podemos usar esses pontos numa coordenada cartesiana e desenha-los
            na tela
         */
        return Path().apply {
            val angle = 2.0 * PI / sides
            val startAngle = PI / 2.0 + Math.toRadians(360.0 / (2 * sides))
            Timber.tag("PATH_ANGLE").i("$angle")
            moveTo(
                cx + (radius * cos(startAngle)).toFloat(),
                cy + (radius * sin(startAngle)).toFloat(),
            )
            (1 until sides).forEach { i ->
                lineTo(
                    cx + (radius * cos(startAngle - angle * i)).toFloat(),
                    cy + (radius * sin(startAngle - angle * i)).toFloat()
                )
            }
            close()
        }
    }
}


class PolygonsView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
): View(context, attr) {
    private val polygonDrawable = PolygonDrawable()

    private lateinit var polygonCanvas: PolygonCanvas

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        polygonCanvas = PolygonCanvas(w, h)
    }
}

class PolygonDrawable : Drawable() {

    var progression = 1f
        set(value) {
            field = value.coerceIn(0f, 1f)
            callback?.invalidateDrawable(this)
        }

    var dotProgress = 0f
        set(value) {
            field = value.coerceIn(0f, 1f)
            callback?.invalidateDrawable(this)
        }

    private val polygons = listOf(
        Polygon(15, 0xffe84c65.toInt(), 362f, 2, PathMeasure()),
        Polygon(14, 0xffe84c65.toInt(), 338f, 3, PathMeasure()),
        Polygon(13, 0xffd554d9.toInt(), 314f, 4, PathMeasure()),
        Polygon(12, 0xffaf6eee.toInt(), 292f, 5, PathMeasure()),
        Polygon(11, 0xff4a4ae6.toInt(), 268f, 6, PathMeasure()),
        Polygon(10, 0xff4294e7.toInt(), 244f, 7, PathMeasure()),
        Polygon(9, 0xff6beeee.toInt(), 220f, 8, PathMeasure()),
        Polygon(8, 0xff42e794.toInt(), 196f, 9, PathMeasure()),
        Polygon(7, 0xff5ae75a.toInt(), 172f, 10, PathMeasure()),
        Polygon(6, 0xffade76b.toInt(), 148f, 11, PathMeasure()),
        Polygon(5, 0xffefefbb.toInt(), 128f, 12, PathMeasure()),
        Polygon(4, 0xffe79442.toInt(), 106f, 13, PathMeasure()),
        Polygon(3, 0xffe84c65.toInt(), 90f, 14, PathMeasure())
    )


    override fun draw(canvas: Canvas) {
        TODO("Not yet implemented")
    }

    override fun setAlpha(alpha: Int) {
        TODO("Not yet implemented")
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT

}

object PROREGSS : FloatProperty<PolygonDrawable>("") {
    override fun setValue(
        polygonDrawable: PolygonDrawable?,
        value: Float
    ) {
        polygonDrawable
    }

    override fun get(polygonDrawable: PolygonDrawable?): Float? {
        TODO("Not yet implemented")
    }

}


private class Polygon(
    val sides: Int,
    val color: Int,
    val radius: Float,
    val laps: Int,
    val pathMeasure: PathMeasure
) {
    
}