package com.br.funwithdatabinding.view.features.books.android2dgraphics.playwithpaths

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    TODO criar uma view como no gif do desse artigo
    https://medium.com/androiddevelopers/playing-with-paths-3fbc679a6f77
    - Slider
    - desenhar um poligono com N lados conforme avançamos a barra
        - minimo 3 (óbvid)
 */
class FunWithSliderAndPolygonsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fun_with_slider_and_polygons)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}