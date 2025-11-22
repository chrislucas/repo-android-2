package com.br.justcomposelabs.tutorial.views.animation.textcounteranimation

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.justcomposelabs.databinding.ActivityTextCounterAnimationBinding


/*
    Codigo auxiliado pela IA para estudar como ValueAnimator funciona
    Pesquisa: ValueAnimator text counter
 */
class TextCounterAnimationActivity : AppCompatActivity() {

    private val binding: ActivityTextCounterAnimationBinding by lazy {
        ActivityTextCounterAnimationBinding.inflate(layoutInflater)
    }

    private val countAnimationFloat = ValueAnimator.ofFloat(0.0F, 100.0F)

    private val countAnimationInt = ValueAnimator.ofInt(0, 100)

    private val timeInterpolation = TimeInterpolator { input ->
        /*
            https://developer.android.com/reference/android/animation/TimeInterpolator
         */

        0.1f * input
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

            countAnimationFloat.duration = 5000L
            countAnimationInt.duration = 5000L
            //animator.interpolator = timeInterpolation

            btReset.setOnClickListener {
                countAnimationFloat.cancel()
                textFloatCounter.text = "0.0"
                textIntCounter.text = "0"
            }

            btStart.setOnClickListener {
                countAnimationFloat.addUpdateListener { animation ->
                    textFloatCounter.text = animation.animatedValue.toString()
                }

                countAnimationInt.addUpdateListener { animation ->
                    textIntCounter.text = animation.animatedValue.toString()
                }

                countAnimationFloat.start()
                countAnimationInt.start()
            }
        }
    }

}