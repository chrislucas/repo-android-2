package com.br.funwithjetpackcompose.tutorials.google.interoperabillity.usingcomposeinviews

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.mylibrary.R


/*
    ViewCompositionStrategy for ComposeView
    https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/compose-in-views?authuser=1#composition-strategy

 */
class ViewCompositionStrategyInComposeViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_composition_strategy_in_compose_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}