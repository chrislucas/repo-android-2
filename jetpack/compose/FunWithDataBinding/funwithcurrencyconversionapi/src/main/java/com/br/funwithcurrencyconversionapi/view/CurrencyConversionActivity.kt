package com.br.funwithcurrencyconversionapi.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithcurrencyconversionapi.R

/**
 * https://medium.com/swlh/how-i-built-a-simple-currency-converter-app-using-recommended-android-pattern-and-architecture-204a3bbfc142
 * API
 * https://currency.getgeoapi.com/
 */
class CurrencyConversionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_currency_conversion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}