package com.br.funwithdatabinding.view.features.tutorials.google.interactwithotherapps.activityresultapis

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.databinding.ActivityResultBinding
import com.br.funwithdatabinding.view.features.tutorials.google.interactwithotherapps.activityresultapis.SimpleResultActivityContract.Companion.KEY_INPUT
import kotlin.properties.Delegates

class SimpleResultActivity : AppCompatActivity() {

    val binding: ActivityResultBinding by lazy {
        ActivityResultBinding.inflate(layoutInflater)
    }

    private var input: Int? = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding.root.run {
            setContentView(this)
            ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        input = intent.getIntExtra(KEY_INPUT, EMPTY_VALUE)
        binding.btnResultOk.setOnClickListener { _ ->
            val intent = Intent().apply {
                putExtra(KEY_RESULT, input)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    companion object {
        const val EMPTY_VALUE = -2
        const val KEY_RESULT = "KEY_RESULT"
    }
}