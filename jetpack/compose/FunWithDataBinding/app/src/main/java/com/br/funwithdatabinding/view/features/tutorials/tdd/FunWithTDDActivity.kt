package com.br.funwithdatabinding.view.features.tutorials.tdd

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    https://medium.com/android-dev-br/testes-em-android-test-driven-development-com-activities-e-custom-views-24d08dccd11a
    ActivityScenario
    https://developer.android.com/reference/androidx/test/core/app/ActivityScenario
    Test your app's activities
    https://developer.android.com/guide/components/activities/testing

    Set up project for AndroidX Test
    https://developer.android.com/training/testing/instrumented-tests/androidx-test-libraries/test-setup

 */
class FunWithTDDActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fun_with_tddactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}