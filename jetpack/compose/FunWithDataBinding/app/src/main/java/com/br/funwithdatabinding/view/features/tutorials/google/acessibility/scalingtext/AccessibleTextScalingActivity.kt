package com.br.funwithdatabinding.view.features.tutorials.google.acessibility.scalingtext

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    TODO
    Accessible Text Scaling for Android
    https://dev.to/sigute/accessible-text-scaling-for-android-1ham

    AutoSize TextViews
    https://developer.android.com/develop/ui/views/text-and-emoji/autosizing-textview
 */
class AccessibleTextScalingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_acessible_text_scaling)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}