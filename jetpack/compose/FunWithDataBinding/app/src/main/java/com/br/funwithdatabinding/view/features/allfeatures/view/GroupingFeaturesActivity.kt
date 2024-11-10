package com.br.funwithdatabinding.view.features.allfeatures.view

import android.app.ActivityGroup
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R
import com.br.funwithdatabinding.databinding.ActivityGroupingFeaturesBinding

/*
    Mostrar uma lista com categorias que agrupam os estudos feitos nesse projeto
    Exemplo
    - SpannableString
    - Custom Views
    - Compose
    - Maps

    TODO
     - a lista de categoria deve ser na horizontal
     - cada categoria vai ter um conjunto de nós filhos de tutoriais
        - esse conjunto de nós será representado por

 */
class GroupingFeaturesActivity : AppCompatActivity() {

    private val bindView: ActivityGroupingFeaturesBinding by lazy {
        ActivityGroupingFeaturesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(bindView.root)
        ViewCompat.setOnApplyWindowInsetsListener(bindView.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}