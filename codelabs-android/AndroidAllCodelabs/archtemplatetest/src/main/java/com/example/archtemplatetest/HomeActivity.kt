package com.example.archtemplatetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/*
    https://github.com/android/architecture-templates
    branch base
    https://github.com/android/architecture-templates/tree/base
    Now In Android
    https://github.com/android/nowinandroid
 */
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}