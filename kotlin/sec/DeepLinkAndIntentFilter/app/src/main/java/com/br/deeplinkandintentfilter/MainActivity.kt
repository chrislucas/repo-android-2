package com.br.deeplinkandintentfilter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val LOCAL_FILE_URI = "file:///android_asset/index.html"
    }

    private val btOpenWebViewActivity: Button by lazy { findViewById(R.id.button_open_web_view_activity) }
    private val btCheckDeeplinkActivity: Button by lazy { findViewById(R.id.button_open_test_deeplink_activity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btOpenWebViewActivity.setOnClickListener {
            val intent = Intent(this, SimpleWebViewActivity::class.java)
            val bundle = Bundle()
            bundle.putString(SimpleWebViewActivity.EXTRA_URL, LOCAL_FILE_URI)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        btCheckDeeplinkActivity.setOnClickListener {
            startActivity(Intent(this, CheckDeeplink::class.java))
        }
    }
}