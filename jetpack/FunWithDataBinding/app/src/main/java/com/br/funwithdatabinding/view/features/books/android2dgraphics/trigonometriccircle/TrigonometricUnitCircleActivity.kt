package com.br.funwithdatabinding.view.features.books.android2dgraphics.trigonometriccircle

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R
import kotlin.math.min
import androidx.core.content.withStyledAttributes
import com.br.funwithdatabinding.databinding.ActivityTrigonomecUnitCircleBinding
import timber.log.Timber
import java.util.Locale
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

/*
    TODO
    Desenhar um circulo trigonometrico
        - explorar funcoes de seno, cos e tangente, secante, cossencate
        - explorar as funcoes trigonometricas inversas
             - escrever os valores das funcoes na tela
        - implmentar funcao que ao tocar num ponto da tela seja desenhado um ponto no
        circulo no mesmo angulo, formando uma projecao
            - escrevdr o ponto que foi tocado na tela (rastreamento)
            - permitir que rastreamento  seja desabilitado
                - talvez atraves de uma custom atttr

        - permitir controlar o tamanho do circulo
        - desenhar graficos das funcoes abaixo do circulo trigonometrico
 */
class TrigonometricUnitCircleActivity : AppCompatActivity() {

    private val binding: ActivityTrigonomecUnitCircleBinding by lazy {
        ActivityTrigonomecUnitCircleBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding.run {
            setContentView(root)
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { e ->
            when (e.actionMasked) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    binding.main.lastTouch = Pair(e.rawX, e.rawY)
                    binding.main.invalidate()
                }
            }
        }
        return true
    }
}


class SimpleUnitCircleView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
) : View(context, attr) {

    /*
        https://developer.android.com/develop/ui/views/layout/custom-views/create-view#applyattr
     */

    private var shouldTrackTouchPointer = true
    private var showCircleCenterText = true

    init {
        context.withStyledAttributes(attr, R.styleable.SimpleUnitCircleView, 0, 0) {
            shouldTrackTouchPointer =
                getBoolean(R.styleable.SimpleUnitCircleView_trackTouchPointer, true)
            showCircleCenterText =
                getBoolean(R.styleable.SimpleUnitCircleView_showCircleCenterText, true)
        }
    }

    private val paintUnitCircle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.RED
    }

    private val paintDotOnCircumference = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLUE
    }


    private val paintTrackTouchEvent = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.RED
        textSize = 60f
    }


    private val paintTrackPolarCoordinate = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.BLUE
        textSize = 60f
    }

    /*
        https://stackoverflow.com/questions/11120392/android-center-text-on-canvas

        Define custom attributes
        https://developer.android.com/develop/ui/views/layout/custom-views/create-view#customattr
     */


    var lastTouch = Pair(0.0f, 0.0f)

    data class CircleDescription(
        var centerX: Float = 0.0f,
        var centerY: Float = 0.0f,
        var mRadius: Float = 0.0f
    ) {
        fun set(cx: Float, cy: Float, r: Float) {
            centerX = cx
            centerY = cy
            mRadius = r
        }
    }

    data class PolarCoordinate(
        val radius: Float,
        private val ox: Float,
        private val oy: Float,
        private val px: Float,
        private val py: Float,
    ) {
        /*
            https://math.stackexchange.com/questions/1923405/how-to-get-the-angle-between-polar-coordinates-without-converting-to-cartesian
            https://stackoverflow.com/questions/14096138/find-the-point-on-a-circle-with-given-center-point-radius-and-degree

            Explicacao
            - angle = atan2(px - ox, py - oy) * 180.0f / PI
            nos da o angulo entre 2 pontos, no caso o centro do circulo e um ponto qualquer no mesmo
            plano

            - Com o angulo em maos podemos calcular o ponto P(x, y) na borda do ciruclo relativo
            ao centro do circulo
                x = r * sin(angle)
                y = r * cos(angle)

                P(x, y) - aqui temos o quao longe o ponto P esta do centro, entao basta somarmos
                ao ponto no centro do circulo

                Seja C(x, y) o ponto no centro do circulo entao
                    B = C + P
                    B é o ponto na borda do circulo relativo ao centro



            https://pt.wikipedia.org/wiki/Coordenadas_polares#
         */
        val angle = atan2(px - ox, py - oy) * 180.0f / PI

        private val pointRelativeToCircleCenter = Pair(
            (radius * sin(angle)).toFloat(),
            (radius * cos(angle)).toFloat()
        )

        val pointOnCircleBorder = Pair(
            pointRelativeToCircleCenter.first + ox,
            pointRelativeToCircleCenter.second + oy
        )
    }

    private var circleDescription = CircleDescription()

    init {
        setBackgroundColor(Color.WHITE)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val (cx, cy, radius) = circleDescription
        paintUnitCircle.strokeWidth = radius * .05f
        canvas.drawCircle(cx, cy, radius, paintUnitCircle)
        if (shouldTrackTouchPointer) {
            drawTextTrackTouchPointer(canvas)
        }

        if (showCircleCenterText) {
            drawTextCenterPointUnitCircle(canvas)
        }

        val polarCoordinate = calcAngleBetweenPoints(
            radius,
            cx,
            cy,
            lastTouch.first,
            lastTouch.second
        )

        drawTextTrackPolarCoordinate(polarCoordinate, canvas)
    }


    private fun calcAngleBetweenPoints(
        radius: Float,
        ox: Float,
        oy: Float,
        px: Float,
        py: Float,
    ) = PolarCoordinate(radius, ox, oy, px, py)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        circleDescription.set(w / 2f, h / 2f, min(w, h) * .7f * .5f)
    }

    private fun drawTextCenterPointUnitCircle(canvas: Canvas) {
        val (cx, cy, radius) = circleDescription
        val text = "Center($cx, $cy)"
        val textCenterCircleBounds = Rect()
        paintTrackTouchEvent.getTextBounds(text, 0, text.length, textCenterCircleBounds)
        val cxTextCenter = cx - textCenterCircleBounds.width() / 2
        val margin = 100
        val cyTextCenter = cy + radius + margin

        canvas.drawText(
            text,
            cxTextCenter,
            cyTextCenter,
            paintTrackTouchEvent
        )
    }

    private fun drawTextTrackTouchPointer(canvas: Canvas) {
        val (cx, cy, radius) = circleDescription
        val margin = 200
        val text = "Last Touch: $lastTouch"
        val textLastTouchPointBounds = Rect()
        paintTrackTouchEvent.getTextBounds(text, 0, text.length, textLastTouchPointBounds)
        val cxTextLastTouchPointBound = cx - textLastTouchPointBounds.width() / 2
        val cyTextLastTouchPointBound = cy + radius + margin
        canvas.drawText(
            text,
            cxTextLastTouchPointBound,
            cyTextLastTouchPointBound,
            paintTrackTouchEvent
        )
    }

    private fun drawTextTrackPolarCoordinate(polarCoordinate: PolarCoordinate, canvas: Canvas) {
        /*
            Custom view SimpleUnitCircleView overrides onTouchEvent but not performClick
            https://stackoverflow.com/questions/27462468/custom-view-overrides-ontouchevent-but-not-performclick
         */
        val (cx, cy, radius) = circleDescription
        val margin = 300

        val formattedAngle = String.format(Locale.getDefault(), "%.2f", polarCoordinate.angle)
        val formattedCartesianCoordinate = String.format(
            Locale.getDefault(), "%s", polarCoordinate.pointOnCircleBorder
        )
        val text = "Angle: $formattedAngle, P: $formattedCartesianCoordinate"
        val textLPolarCoordinateBounds = Rect()
        paintTrackPolarCoordinate.getTextBounds(text, 0, text.length, textLPolarCoordinateBounds)
        val cxTextLastTouchPointBound = cx - textLPolarCoordinateBounds.width() / 2
        val cyTextLastTouchPointBound = cy + radius + margin
        canvas.drawText(
            text,
            cxTextLastTouchPointBound,
            cyTextLastTouchPointBound,
            paintTrackPolarCoordinate
        )

        val (px, py) = polarCoordinate.pointOnCircleBorder
        drawDotOnBorderUnitCircle(canvas, px, py)
    }

    private fun drawDotOnBorderUnitCircle(canvas: Canvas, cx: Float, cy: Float) {
        val radius = 30.0f
        paintDotOnCircumference.strokeWidth = radius * .1f
        canvas.drawCircle(cx, cy, radius, paintDotOnCircumference)
    }
}


class TrackVelocity() {

    private val mVelocityTracker: VelocityTracker = VelocityTracker.obtain()

    fun track(event: MotionEvent) {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                mVelocityTracker.clear()
                mVelocityTracker.addMovement(event)
            }

            MotionEvent.ACTION_MOVE -> {
                mVelocityTracker.run {
                    val pointerId = event.getPointerId(event.actionIndex)
                    addMovement(event)
                    computeCurrentVelocity(1000)
                    Timber.tag(DEBUG_TAG)
                        .d("Velocity: P(${getXVelocity(pointerId)}, ${getYVelocity(pointerId)})")
                }
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                mVelocityTracker.recycle()
            }
        }
    }


    companion object {
        private const val DEBUG_TAG = "Velocity"
    }

}