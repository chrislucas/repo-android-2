package com.br.funwithdatabinding.view.features.tutorials.medium.recyclerview.pagination

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    TODO
    Visão geral da biblioteca Paging
    https://developer.android.com/topic/libraries/architecture/paging/v3-overview?hl=pt-br#kts

    Migrar para a Paging 3
    https://developer.android.com/topic/libraries/architecture/paging/v3-migration?hl=pt-br

    Paging3 — Doing Recyclerview Pagination the Right Way
    https://medium.com/swlh/paging3-recyclerview-pagination-made-easy-333c7dfa8797
 */

class DoingRecyclerViewPaginationWithPaging3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_doing_recycler_view_pagination_with_paging3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}