package com.br.funwithdatabinding.view.features.tutorials.google.edgetoedge

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    TODO
    https://developer.android.com/develop/ui/views/layout/edge-to-edge

    Insets handling tips for Android 15’s edge-to-edge enforcement
    https://medium.com/androiddevelopers/insets-handling-tips-for-android-15s-edge-to-edge-enforcement-872774e8839b

    WindowInsets — listeners to layouts
    https://medium.com/androiddevelopers/windowinsets-listeners-to-layouts-8f9ccc8fa4d1
 */
class DisplayContentEdgeToEdgeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_display_content_edge_to_edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}