package com.br.funwithdatabinding.view.features.tutorials.medium.spannable.absoluteandrelatiespan

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    TODO
    https://stackoverflow.com/questions/16335178/different-font-size-of-strings-in-the-same-textview

    TODO explorar
    Spans
    https://developer.android.com/develop/ui/views/text-and-emoji/spans
 */
class AbsoluteAndRelativeSpanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_absolute_and_relative_span)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}