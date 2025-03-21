package com.br.funwithdatabinding.view.features.tutorials.medium.recyclerview.pagination

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    https://medium.com/@mohitrajput987/recyclerview-pagination-scroll-listener-in-android-98e14ce911b

    TODO
    leitura https://thegraduateguy.medium.com/pagination-with-recyclerview-in-android-2506c4d09a5c
 */
class RecyclerViewPaginationScrollListenerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view_pagination_scroll_listener)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}