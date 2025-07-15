package com.br.funwithjetpackcompose.tutorials.google.codelabs.lifecycleaware.features.simplestart

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.mylibrary.R
import com.br.mylibrary.databinding.ActivityChronoBinding

class SimpleStartChronometerActivity : AppCompatActivity() {

    private val binder: ActivityChronoBinding by lazy {
        ActivityChronoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binder.run {
            setContentView(this.root)
            ViewCompat.setOnApplyWindowInsetsListener(this.root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            this.simpleStartChronometer.start()
        }
    }
}