package com.br.funwithjetpackcompose.tutorials.medium.makeanyclasslifecycleaware

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.mylibrary.R

class DefaultLifecycleObserverActivity : AppCompatActivity() {

    private val logger = LifecycleAwareLogger.create("DefaultLifecyclerObserverActivity")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_default_lifecycler_observer)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        logger.enableLifecycleLogging()
        lifecycle.addObserver(logger)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(logger)
    }
}