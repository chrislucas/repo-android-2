package com.br.funwithfragments.tutorials.medium.advancedwaycommunicate

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithfragments.R

/*
    https://medium.com/@dev.chaaba/advanced-way-to-communicate-with-fragments-activities-and-parent-fragment-419646b0ec13
    https://github.com/ChaaDevAndroid/fragmentcom/blob/master/app/src/main/java/com/example/fragmentCom/utils/utils.kt
 */
class AdvancedWayToCommunicateFragmentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_advanced_way_to_communicate_fragments)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}