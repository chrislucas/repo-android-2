package com.br.funwithdatabinding.view.features.tutorials.funwithspannablestringbuilder

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/**
 * @see com.br.funwithdatabinding.view.features.tutorials.google.span.dynamicspandrawable.FunWithDynamicSpanDrawableActivity
    TODO -
    Criar um mecanismo que pegue o tamanho da string S crie un intervalo 0 a S e permita
    que o usuario escolha esse intervalo para modificar o tamanho do SpannableStringBuilder

    abaixo
    Range Slider
    https://medium.com/analytics-vidhya/sliders-material-component-for-android-5be61bbe6726

 */

class FunWithSpannableStringBuiliderAndRangeSliderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fun_with_spannable_string_builider_and_range_slider)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}