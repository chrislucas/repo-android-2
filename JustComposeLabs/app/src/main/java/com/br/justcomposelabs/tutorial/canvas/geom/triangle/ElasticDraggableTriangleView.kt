package com.br.justcomposelabs.tutorial.canvas.geom.triangle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.*

class ElasticDraggableTriangleView @JvmOverloads constructor(
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
    private val originalEdgeLengths = arrayOf(
        distance(vertices[0], vertices[1]),
        distance(vertices[1], vertices[2]),
        distance(vertices[2], vertices[0])
    )
    private var selectedVertexIndex: Int = -1
    private val touchTolerance = 50f
    private val elasticStrength = 0.1f // Adjust this value to control the elasticity strength

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
                    // Move the selected vertex
                    vertices[selectedVertexIndex].x = event.x
                    vertices[selectedVertexIndex].y = event.y

                    // Apply elastic effect to the adjacent edges
                    applyElasticEffect(selectedVertexIndex)

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

    // Helper function to compute distance between two PointF objects
    private fun distance(p1: PointF, p2: PointF): Float {
        return distance(p1.x, p1.y, p2.x, p2.y)
    }

    // Apply elastic effect to the edges connected to the moved vertex
    private fun applyElasticEffect(movedVertexIndex: Int) {
        val prevVertexIndex = (movedVertexIndex + 2) % 3
        val nextVertexIndex = (movedVertexIndex + 1) % 3

        // Apply elastic effect to the previous edge
        adjustVertex(prevVertexIndex, movedVertexIndex, originalEdgeLengths[prevVertexIndex])

        // Apply elastic effect to the next edge
        adjustVertex(nextVertexIndex, movedVertexIndex, originalEdgeLengths[nextVertexIndex])
    }

    // Adjust the position of a vertex to maintain the original edge length
    private fun adjustVertex(vertexIndex: Int, movedVertexIndex: Int, originalLength: Float) {
        val vertex = vertices[vertexIndex]
        val movedVertex = vertices[movedVertexIndex]

        val currentLength = distance(vertex, movedVertex)
        val lengthDifference = currentLength - originalLength

        if (lengthDifference != 0f) {
            val dx = movedVertex.x - vertex.x
            val dy = movedVertex.y - vertex.y
            val angle = atan2(dy, dx)

            val adjustmentX = cos(angle) * lengthDifference * elasticStrength
            val adjustmentY = sin(angle) * lengthDifference * elasticStrength

            vertex.x += adjustmentX
            vertex.y += adjustmentY
        }
    }
}
