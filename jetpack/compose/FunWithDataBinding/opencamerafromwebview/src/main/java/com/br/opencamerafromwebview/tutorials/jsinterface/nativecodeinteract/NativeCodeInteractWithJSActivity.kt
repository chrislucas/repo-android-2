package com.br.opencamerafromwebview.tutorials.jsinterface.nativecodeinteract

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.opencamerafromwebview.R

/*
    https://chicio.medium.com/web-to-native-code-communication-on-android-using-javascript-interfaces-f66f633dfc2d
    JavascriptInterface
    https://developer.android.com/reference/android/webkit/JavascriptInterface
    - Annotation that allows exposing methods to JavaScript.
 */
class NativeCodeInteractWithJSActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_native_code_interact_with_jsactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}