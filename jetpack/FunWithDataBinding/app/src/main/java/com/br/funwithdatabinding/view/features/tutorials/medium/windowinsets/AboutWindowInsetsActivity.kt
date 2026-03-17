package com.br.funwithdatabinding.view.features.tutorials.medium.windowinsets

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    View.OnApplyWindowInsetsListener
    https://developer.android.com/reference/android/view/View.OnApplyWindowInsetsListener

    WindowInsets — listeners to layouts
    https://medium.com/androiddevelopers/windowinsets-listeners-to-layouts-8f9ccc8fa4d1
 */
class AboutWindowInsetsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about_window_insets)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}