package com.br.funwithdatabinding.view.features.allfeatures.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.br.funwithdatabinding.R
import com.br.funwithdatabinding.databinding.ActivityAllFeaturesBinding
import com.br.funwithdatabinding.view.features.allfeatures.model.getFeatures
import com.br.recyclerviewcomponent.behavior.FactoryViewHolderDefault
import com.br.recyclerviewcomponent.view.adapter.RecyclerViewAdapter

class AllFeaturesActivity : AppCompatActivity() {


    private val viewBinder = ActivityAllFeaturesBinding.inflate(layoutInflater)
    private val allFeatures: RecyclerView = viewBinder.allFeatures

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(viewBinder.root)
        ViewCompat.setOnApplyWindowInsetsListener(viewBinder.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        allFeatures.adapter = RecyclerViewAdapter(
            getFeatures(this),
            FactoryViewHolderDefault()
        )
    }
}