package com.br.justcomposelabs.tutorial.canvas.geom.triangle

// ElasticDraggableTriangleViewII

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.*

class ElasticDraggableTriangleViewII @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // Paint objects for drawing
    private val trianglePaint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.FILL_AND_STROKE
        strokeWidth = 4f
        isAntiAlias = true
    }

    private val pointPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    // Vertices of the triangle
    private val vertices = arrayOf(PointF(300f, 300f), PointF(600f, 300f), PointF(450f, 600f))
    private var selectedVertexIndex: Int = -1
    private val touchTolerance = 50f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw the triangle by connecting vertices
        for (i in 0..2) {
            val start = vertices[i]
            val end = vertices[(i + 1) % 3]
            canvas.drawLine(start.x, start.y, end.x, end.y, trianglePaint)
        }

        // Draw vertices as circles and their coordinate labels
        for (vertex in vertices) {
            canvas.drawCircle(vertex.x, vertex.y, 20f, pointPaint)
            val coordText = "X: ${vertex.x.toInt()} Y: ${vertex.y.toInt()}"
            canvas.drawText(coordText, vertex.x + 25f, vertex.y, pointPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // Detect if touch is near a vertex
                for (i in vertices.indices) {
                    if (distance(event.x, event.y, vertices[i].x, vertices[i].y) < touchTolerance) {
                        selectedVertexIndex = i
                        return true
                    }
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (selectedVertexIndex != -1) {
                    // Move only the selected vertex
                    vertices[selectedVertexIndex].x = event.x
                    vertices[selectedVertexIndex].y = event.y
                    invalidate()
                    return true
                }
            }
            MotionEvent.ACTION_UP -> {
                selectedVertexIndex = -1
            }
        }
        return true
    }

    // Helper function to compute distance between two points
    private fun distance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        return sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
    }
}
