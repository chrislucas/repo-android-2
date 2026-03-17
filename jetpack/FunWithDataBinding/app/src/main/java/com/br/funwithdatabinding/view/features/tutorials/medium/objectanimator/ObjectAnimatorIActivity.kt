package com.br.funwithdatabinding.view.features.tutorials.medium.objectanimator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    TODO
    https://www.geeksforgeeks.org/objectanimator-in-android-with-example/

    Property Animation Overview
    https://developer.android.com/develop/ui/views/animations/prop-animation#object-animator

    https://developer.android.com/reference/android/animation/ObjectAnimator
 */
class ObjectAnimatorIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_object_animator_iactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}