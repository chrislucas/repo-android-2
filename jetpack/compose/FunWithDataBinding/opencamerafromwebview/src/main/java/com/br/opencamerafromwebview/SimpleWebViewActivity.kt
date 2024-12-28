package com.br.opencamerafromwebview

import android.os.Bundle
import android.util.Log
import android.view.Window
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.opencamerafromwebview.databinding.ActivitySimpleWebviewBinding

class SimpleWebViewActivity : AppCompatActivity() {

    private val binding: ActivitySimpleWebviewBinding by lazy {
        ActivitySimpleWebviewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding.run {
            setContentView(root)
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            webView.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    /*
                    setTitle("Loading...");
                    setProgress(newProgress * 100);
                    //requestWindowFeature(Window.FEATURE_PROGRESS)
                    supportRequestWindowFeature(Window.FEATURE_PROGRESS)

                     */
                    Log.i("PROGRESS_LOAD", "Progress: $newProgress | URL: ${webView.url}")
                }
            }
            webView.loadUrl("https://developer.android.com")
        }
    }
}