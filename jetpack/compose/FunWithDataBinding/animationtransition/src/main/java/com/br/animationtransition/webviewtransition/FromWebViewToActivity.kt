package com.br.animationtransition.webviewtransition

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.animationtransition.R

/*
    TODO
    https://stackoverflow.com/questions/54768003/redirecting-from-a-web-view-to-a-new-activity

    https://stackoverflow.com/questions/25406376/start-activity-from-webview/25406560
    https://stackoverflow.com/questions/44941741/open-activity-after-clicking-inside-webview
 */
class FromWebViewToActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_from_web_view_to)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}