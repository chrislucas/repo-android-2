package com.br.funwithdatabinding.view.features.tutorials.buttonprogressbar

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R
import com.br.funwithdatabinding.databinding.ActivityFunWithLoadingButtonBinding
import com.google.android.material.button.MaterialButton

/*
    https://stackoverflow.com/questions/40408903/make-progress-bar-inside-button
    https://stackoverflow.com/questions/55149715/button-click-progress-animation
 */
class FunWithLoadingButtonActivity : AppCompatActivity() {

    private val binding: ActivityFunWithLoadingButtonBinding by lazy {
        ActivityFunWithLoadingButtonBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding.run {
            setContentView(this.root)
            ViewCompat.setOnApplyWindowInsetsListener(this.root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            binding.loadingButton.setOnClickListener {
                binding.loadingButton.showLoading()
                Looper.myLooper()?.let { looper ->
                    Handler(looper).postDelayed({
                        binding.loadingButton.hideLoading()
                    }, 10000)
                }
            }
        }
    }
}


class LoadingButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) { // Mudança aqui

    private val button: MaterialButton
    private val progressBar: ProgressBar
    private var text: String? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_loading_button, this, true)
        button = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)
    }

    fun showLoading() {
        button.visibility = GONE
        progressBar.visibility = VISIBLE
    }

    fun hideLoading() {
        button.visibility = VISIBLE
        progressBar.visibility = GONE
    }
}

