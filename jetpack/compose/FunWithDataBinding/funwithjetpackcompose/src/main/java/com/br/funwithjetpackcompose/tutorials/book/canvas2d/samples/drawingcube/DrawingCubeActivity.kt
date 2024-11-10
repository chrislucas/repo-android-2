package com.br.funwithjetpackcompose.tutorials.book.canvas2d.samples.drawingcube

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithjetpackcompose.tutorials.book.canvas2d.samples.drawingcube.MatrixOperation.multiply
import com.br.funwithjetpackcompose.tutorials.book.canvas2d.samples.drawingcube.MatrixOperation.multiplyByConst
import com.br.funwithjetpackcompose.tutorials.book.canvas2d.samples.drawingcube.MatrixOperation.translate
import com.br.funwithjetpackcompose.tutorials.book.canvas2d.samples.drawingcube.MatrixOperation.vecToMatrix
import com.br.mylibrary.databinding.ActivityDrawingCubeBinding
import kotlin.math.cos
import kotlin.math.sin

/*
    https://github.com/VictorFranco/AndroidCube/blob/main/app/src/main/java/com/example/cube/MainActivity.java
 */
class DrawingCubeActivity : AppCompatActivity() {

    private val binding: ActivityDrawingCubeBinding by lazy {
        ActivityDrawingCubeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(AnimatedCubeCanvas(this))
    }

    private fun test() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}


class AnimatedCubeCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    styleAttr: Int = 0
) : View(context, attrs, styleAttr) {

    private val vertices: Array<Array<Double>> = Array(8) { Array(3) { 0.0 } }
    private val projectedVertices: Array<Array<Double>> = Array(8) { Array(3) { 0.0 } }

    private val paintCubeEdge = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 20.0f
        strokeCap = Paint.Cap.ROUND
        color = Color.BLACK
    }

    private val paintAngleLog = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 20.0f
        strokeCap = Paint.Cap.ROUND
        color = Color.BLACK
    }

    private var angle = 0.0

    init {
        var counter = 0
        for (i in -1..1 step 2) {
            for (j in -1..1 step 2) {
                for (k in -1..1 step 2) {
                    vertices[counter] = arrayOf(i * 1.0, j * 1.0, k * 1.0)
                    counter += 1
                }
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val rotateZ = arrayOf(
            arrayOf(cos(angle), -sin(angle), 0.0),
            arrayOf(sin(angle), cos(angle), 0.0),
            arrayOf(0.0, 0.0, 1.0)
        )

        val rotateY = arrayOf(
            arrayOf(cos(angle), 0.0, -sin(angle)),
            arrayOf(0.0, 1.0, 0.0),
            arrayOf(sin(angle), 0.0, cos(angle), 0.0)
        )

        val rotateX = arrayOf(
            arrayOf(1.0, 0.0, 0.0),
            arrayOf(0.0, cos(angle), -sin(angle)),
            arrayOf(0.0, sin(angle), cos(angle))
        )

        drawVertices(rotateX, rotateY, rotateZ, canvas)
        connectedVertices(canvas)
        angle += ANGLE_OFFSET
        showAngle(canvas)
        handler.postDelayed({ invalidate() }, 10)
    }

    private fun showAngle(canvas: Canvas) {
        canvas.drawText("Ângulo: $angle", 100.0f, 200.0f, paintAngleLog)
    }

    private fun connectedVertices(canvas: Canvas) {
        val relationAmongPoints = arrayOf(0, 1, 3, 2)
        for (idx in relationAmongPoints.indices) {
            val modularIdx = (idx + 1) % relationAmongPoints.size

            connectVertices(
                relationAmongPoints[idx],
                relationAmongPoints[modularIdx],
                canvas,
                paintCubeEdge,
                projectedVertices
            )

            connectVertices(
                relationAmongPoints[idx] + 4,
                relationAmongPoints[modularIdx] + 4,
                canvas,
                paintCubeEdge,
                projectedVertices
            )


            connectVertices(
                relationAmongPoints[idx],
                relationAmongPoints[idx] + 4,
                canvas,
                paintCubeEdge,
                projectedVertices
            )
        }
    }

    private fun connectVertices(
        p: Int, q: Int, canvas: Canvas, paint: Paint, vertices: Array<Array<Double>>
    ) {
        val vertexP = vertices[p]
        val vertexQ = vertices[q]
        canvas.drawLine(
            vertexP[0].toFloat(),
            vertexP[1].toFloat(),
            vertexQ[0].toFloat(),
            vertexQ[1].toFloat(),
            paint
        )
    }

    private fun drawVertices(
        rotateX: Array<Array<Double>>,
        rotateY: Array<Array<Double>>,
        rotateZ: Array<Array<Double>>,
        canvas: Canvas
    ) {
        val distance = 4
        for (i in 0 until 8) {
            var rotated = multiply(
                rotateZ,
                vecToMatrix(vertices[i])
            )
            rotated = multiply(rotateX, rotated)
            rotated = multiply(rotateY, rotated)

            val z = 1.0 / (distance - rotated[2][0])
            val projection = arrayOf(
                arrayOf(z, 0.0, 0.0),
                arrayOf(0.0, z, 0.0)
            )

            var projected = multiply(projection, rotated)
            projected = multiplyByConst(500.0, projected)
            val middle = Pair(canvas.width / 2.0, canvas.height / 2.0)
            projected = translate(middle, projected)
            projectedVertices[i][0] = projected[0][0]
            projectedVertices[i][1] = projected[1][0]
        }
    }

    companion object {
        private const val ANGLE_OFFSET = 0.005
    }

}

object MatrixOperation {
    /*
        https://github.com/VictorFranco/AndroidCube/blob/main/app/src/main/java/com/example/cube/MatrixOperations.java
     */


    fun multiply(
        matrixA: Array<Array<Double>>,
        matrixB: Array<Array<Double>>
    ): Array<Array<Double>> {

        val lineA = matrixA.size
        val columnA = matrixA[0].size
        val lineB = matrixB.size
        val columnB = matrixB[0].size

        if (columnA != lineB) {
            throw Exception("")
        }

        val result = Array(lineA) { Array(columnB) { 0.0 } }

        for (i in 0 until lineA) {
            for (j in 0 until columnB) {
                for (k in 0 until columnA) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j]
                }
            }
        }

        return result
    }

    fun toMatrix(points: Array<Double>): Array<Array<Double>> {
        val col = points.size
        val matrix = Array(col) { Array(1) { 0.0 } }
        for (i in 0 until col) {
            matrix[i][0] = points[i]
        }
        return matrix
    }

    fun multiplyByConst(constant: Double, matrix: Array<Array<Double>>): Array<Array<Double>> {
        val result = Array(matrix.size) { Array(matrix[0].size) { 0.0 } }
        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                result[i][j] = matrix[i][j] * constant
            }
        }
        return result
    }

    fun translate(
        pairXY: Pair<Double, Double>,
        points: Array<Array<Double>>
    ): Array<Array<Double>> {
        val (x, y) = pairXY
        val result = Array(2) { Array(1) { 0.0 } }
        result[0][0] = points[0][0] + x
        result[1][0] = points[1][0] + y
        return result
    }

    fun vecToMatrix(points: Array<Double>): Array<Array<Double>> {
        val result = Array(points.size) { Array(1) { 0.0 } }
        for (i in points.indices) {
            result[i][0] = points[i]
        }
        return result
    }

    fun printMatrix(matrix: Array<Array<Double>>) {
        val mutableList = mutableListOf<List<Double>>()
        for (col in matrix) {
            mutableList += col.toList()
        }
        println(mutableList.toList())
    }

    fun toFloat(matrix: Array<Array<Double>>): Array<Array<Float>> {
        val result = Array(matrix.size) { Array(matrix[0].size) { 0.0f } }
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                result[i][j] = matrix[i][j].toFloat()
            }
        }
        return result
    }
}