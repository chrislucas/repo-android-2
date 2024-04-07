package com.br.studyacessibility

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * AccessibilityService
 * https://developer.android.com/reference/android/accessibilityservice/AccessibilityService
 *
 * Create your own accessibility service
 * https://developer.android.com/guide/topics/ui/accessibility/service
 *
 * Starting Android Accessibility
 * https://developer.android.com/codelabs/starting-android-accessibility#0
 *
 * Developing an Accessibility Service for Android
 * https://codelabs.developers.google.com/codelabs/developing-android-a11y-service#0
 *
 * Accessibility in Jetpack Compose
 * https://developer.android.com/codelabs/jetpack-compose-accessibility#0
 *
 *
 */

class ApiAndroidAccessibilityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_api_android_accessibility)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}