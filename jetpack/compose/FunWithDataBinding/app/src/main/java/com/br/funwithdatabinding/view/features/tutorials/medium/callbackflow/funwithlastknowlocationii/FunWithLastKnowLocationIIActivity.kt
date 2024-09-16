package com.br.funwithdatabinding.view.features.tutorials.medium.callbackflow.funwithlastknowlocationii

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/**
 * https://manuelvivo.dev/simplifying-apis-coroutines
 *
 */
class FunWithLastKnowLocationIIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fun_with_last_know_location_ii_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recycler_view_all_features)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}