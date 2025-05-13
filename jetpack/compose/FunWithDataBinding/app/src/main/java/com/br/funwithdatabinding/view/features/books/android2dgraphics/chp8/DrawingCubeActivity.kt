package com.br.funwithdatabinding.view.features.books.android2dgraphics.chp8

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    https://github.com/VictorFranco/AndroidCube/tree/main
 */
class DrawingCubeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_drawing_cube)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
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

}