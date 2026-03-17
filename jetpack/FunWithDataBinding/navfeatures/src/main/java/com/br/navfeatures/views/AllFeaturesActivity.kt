package com.br.navfeatures.views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.navfeatures.databinding.ActivityAllFeaturesBinding
import com.br.navfeatures.model.Feature
import com.br.recyclerviewcomponent.behavior.FactoryViewHolderDefault
import com.br.recyclerviewcomponent.model.ViewElement
import com.br.recyclerviewcomponent.view.adapter.RecyclerViewAdapter

class AllFeaturesActivity : AppCompatActivity() {

    private val viewBinder: ActivityAllFeaturesBinding by lazy {
        ActivityAllFeaturesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(viewBinder.root)
        ViewCompat.setOnApplyWindowInsetsListener(viewBinder.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val allFeatures: RecyclerView = viewBinder.recyclerViewAllFeatures
        allFeatures.adapter = RecyclerViewAdapter(
            listOf<ViewElement<Feature, ViewHolder>>(),
            FactoryViewHolderDefault()
        )
    }
}