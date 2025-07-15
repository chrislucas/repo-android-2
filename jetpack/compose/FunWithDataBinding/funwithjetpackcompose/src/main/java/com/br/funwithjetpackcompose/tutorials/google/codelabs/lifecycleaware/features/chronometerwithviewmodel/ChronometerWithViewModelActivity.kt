package com.br.funwithjetpackcompose.tutorials.google.codelabs.lifecycleaware.features.chronometerwithviewmodel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.br.mylibrary.R
import com.br.mylibrary.databinding.ActivityChronometerWithViewModelBinding

class ChronometerWithViewModelActivity : AppCompatActivity() {


    private val binder: ActivityChronometerWithViewModelBinding by lazy {
        ActivityChronometerWithViewModelBinding.inflate(layoutInflater)
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
        }

        val chronometerViewModel = ViewModelProvider(this)[ChronometerViewModel::class.java]
        if (chronometerViewModel.startTime == 0L) {
            chronometerViewModel.startTime = System.currentTimeMillis()
        }
        binder.chronometerWithViewmodel.base = chronometerViewModel.startTime
        binder.chronometerWithViewmodel.start()
    }
}