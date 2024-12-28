package com.br.funwithdatabinding.view.features.customstatedrawable

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R


/*
    https://bterczynski.medium.com/android-drawable-with-custom-states-c6ebdcc58205
    https://developer.android.com/guide/topics/resources/drawable-resource
    https://medium.com/@grzesiek.matyszczak/how-to-modify-drawables-programmatically-on-android-e851c74cc74e

    StateListDrawable
    https://developer.android.com/reference/android/graphics/drawable/StateListDrawable
 */
class CustomStateDrawableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custom_state_drawable)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}