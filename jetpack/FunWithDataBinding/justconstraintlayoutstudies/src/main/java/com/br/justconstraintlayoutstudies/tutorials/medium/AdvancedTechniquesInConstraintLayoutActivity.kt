package com.br.justconstraintlayoutstudies.tutorials.medium

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.justconstraintlayoutstudies.R

/*
    TODO
    https://medium.com/@sandeepkella23/constraintlayout-is-a-powerful-layout-manager-in-android-that-allows-for-flexible-and-complex-76d3547f1f15
 */

class AdvancedTechniquesInConstraintLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_advanced_techniques_in_constraint_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}