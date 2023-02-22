package com.codelabs.advkotlin.canvas.drawing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codelabs.advkotlin.canvas.drawing.view.CanvasView
import com.codelabs.advkotlin.creatingeffecswithshaders.R
import com.codelabs.advkotlin.creatingeffecswithshaders.databinding.ActivityDrawingCanvasBinding

/*
    https://developer.android.com/codelabs/advanced-android-kotlin-training-canvas#0

    - Canvas:  defines shapes that you can draw on the screen.
         https://developer.android.com/reference/android/graphics/Canvas
    - Paint: defines the color, style, font, and so forth, of each shape you draw.
        https://developer.android.com/reference/android/graphics/Paint.html

 */

class DrawingCanvasActivity : AppCompatActivity() {

    private val binding: ActivityDrawingCanvasBinding by lazy {
        ActivityDrawingCanvasBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawing_canvas)


        binding.canvasView.contentDescription = getString(R.string.canvasContentDescription)
    }
}