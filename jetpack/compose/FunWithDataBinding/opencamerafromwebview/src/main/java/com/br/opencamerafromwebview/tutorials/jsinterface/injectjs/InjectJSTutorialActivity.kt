package com.br.opencamerafromwebview.tutorials.jsinterface.injectjs

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.opencamerafromwebview.R

/*
    https://medium.com/@skywall/inject-js-into-androids-webview-8845fb5902b7
    JavascriptInterface
 *  https://developer.android.com/reference/android/webkit/JavascriptInterface
 *     - Annotation that allows exposing methods to JavaScript.
 */
class InjectJSTutorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inject_jstutorial)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}