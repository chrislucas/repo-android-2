package com.br.justcomposelabs.tutorial.views.numberaddremoveanimtion

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.justcomposelabs.R
import com.br.justcomposelabs.databinding.ActivityAddAndRemoveValueAnimtionBinding

class AddAndRemoveValueAnimationActivity : AppCompatActivity() {

    private val binding: ActivityAddAndRemoveValueAnimtionBinding by lazy {
        ActivityAddAndRemoveValueAnimtionBinding.inflate(layoutInflater)
    }

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        with(binding) {
            setContentView(root)

            ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(
                    systemBars.left, systemBars.top,
                    systemBars.right, systemBars.bottom
                )
                insets
            }

            binding.btAdd.setOnClickListener {
                counter+=VALUE%MOD
                binding.tvQuantity.text = counter.toString()
            }

            binding.btRemove.setOnClickListener {
                counter-=VALUE%MOD
                binding.tvQuantity.text = counter.toString()
            }

            binding.tvQuantity.text = counter.toString()
        }
    }

    companion object {
        const val VALUE = 100
        const val MOD = 100000
    }
}