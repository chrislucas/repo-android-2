package com.br.justcomposelabs.tutorial.google.view.recyclerviews

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.justcomposelabs.R
import com.br.justcomposelabs.databinding.ActivityCircularRecyclerViewBinding

class CircularRecyclerViewActivity : AppCompatActivity() {
    private val binder: ActivityCircularRecyclerViewBinding by lazy {
        ActivityCircularRecyclerViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binder.run {
            setContentView(root)
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            circularRecyclerView.adapter = CircularAdapter(
                (1..5).toList(),
                { parentGroup: ViewGroup, viewType: Int ->
                    CircularDefaultViewHolder(parentGroup)
                },
                { c: CircularDefaultViewHolder, data: Int ->
                    c.setContent("$data")
                }
            )
        }
    }
}