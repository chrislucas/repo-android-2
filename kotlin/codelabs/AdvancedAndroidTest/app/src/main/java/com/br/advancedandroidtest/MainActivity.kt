package com.br.advancedandroidtest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * 5.1
 * https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-basics#0
 * 5.2
 * https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-test-doubles#0
 * 5.3
 * Advanced Android in Kotlin 05.3:Testing Coroutines and Jetpack integrations
 * https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-survey?hl=en#0
 *
 * Projeto base
 * https://github.com/google-developer-training/advanced-android-testing
 *
 * Starting Android Accessibility
 * https://developer.android.com/codelabs/starting-android-accessibility?hl=en#0
 *
 *
 * Site de pesquisa de codelabbs
 * https://developer.android.com/get-started/codelabs?category=androidkotlin
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}