package com.br.deeplinkandintentfilter

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class SimpleWebViewActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_URL = "EXTRA_URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrapper_webview)

        val url: String? = intent.extras?.getString(EXTRA_URL, "")

        url?.let {
            val webView: WebView = findViewById(R.id.webview)
            webView.loadUrl(it)
        }
    }
}