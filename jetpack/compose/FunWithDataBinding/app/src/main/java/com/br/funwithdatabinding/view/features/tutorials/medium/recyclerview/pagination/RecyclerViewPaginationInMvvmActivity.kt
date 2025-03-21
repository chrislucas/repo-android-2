package com.br.funwithdatabinding.view.features.tutorials.medium.recyclerview.pagination

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    https://dev.to/ajinkya_patil/recyclerview-pagination-in-mvvmmodel-view-viewmodel-architecture-using-scroll-listener-5cm5

     // TODO
    // https://gist.github.com/pratikbutani/dc6b963aa12200b3ad88aecd0d103872
    // https://blog.iamsuleiman.com/android-pagination-tutorial-getting-started-recyclerview/
 */
class RecyclerViewPaginationInMvvmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view_pagination_in_mvvm)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}