package com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava2.features.searchbuslines.view

import android.os.Bundle
import android.view.KeyEvent
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.br.funwithdatabinding.databinding.ActivitySearchBusLinesSpTransportBinding
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.service.FirebaseConfigRepository
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava2.features.searchbuslines.view.adapter.BusLineRecyclerViewAdapter
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava2.features.searchbuslines.view.viewmodel.BusLinesSpTransViewModel

class SearchBusLinesSpTransportActivity : AppCompatActivity() {

    private val viewBinder: ActivitySearchBusLinesSpTransportBinding by lazy {
        ActivitySearchBusLinesSpTransportBinding.inflate(
            layoutInflater
        )
    }

    private val viewModel: BusLinesSpTransViewModel by lazy {
        ViewModelProvider(this)[BusLinesSpTransViewModel::class.java]
    }

    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(viewBinder.root)
        ViewCompat.setOnApplyWindowInsetsListener(viewBinder.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
            TODO criar um ViewModel para observar esse token
         */
        FirebaseConfigRepository.fetchToken(this) { token ->
            if (token != null) {
                this.token = token
            }
        }
        with(viewBinder.searchViewBusLine) {
            // https://www.youtube.com/watch?v=aQ40mQF4O4s
            editText.setOnEditorActionListener { v, actionId, event ->
                viewBinder.searchViewBusLine.hide()
                viewBinder.searchTopBar.setText(v.text)
                token?.let { viewModel.fetchBusLine(it, v.text.toString()) } ?: run {
                    Toast.makeText(
                        this@SearchBusLinesSpTransportActivity,
                        "Buscando o Token!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                true
            }
        }

        viewBinder.busLinesResultSearch.adapter = BusLineRecyclerViewAdapter()
    }
}