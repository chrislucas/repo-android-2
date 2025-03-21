package com.br.funwithdatabinding.view.features.tutorials.medium.strikethroughtext

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    TODO
    https://zizhao.medium.com/animated-strike-through-on-android-textview-3efe8eddf1ab

    TODO
    https://medium.com/@appdevinsights/under-line-and-strike-through-a-textview-ecacd6736a90

    TODO
    https://mobikul.com/android-strike-textview/
 */
class AnimatedStrikeThroughTextViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_animated_strike_through_text_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}