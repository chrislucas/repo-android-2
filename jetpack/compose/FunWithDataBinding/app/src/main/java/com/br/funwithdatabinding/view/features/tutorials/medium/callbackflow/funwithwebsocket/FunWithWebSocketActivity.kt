package com.br.funwithdatabinding.view.features.tutorials.medium.callbackflow.funwithwebsocket

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/**
 * https://proandroiddev.com/callbacks-in-a-mad-world-wrapping-your-old-callback-listeners-with-callbackflow-863f9e146281
 *
 * OkHttp
 * https://square.github.io/okhttp/5.x/okhttp/okhttp3/-web-socket-listener/index.html
 *
 */
class FunWithWebSocketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fun_with_web_socket)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recycler_view_all_features)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}