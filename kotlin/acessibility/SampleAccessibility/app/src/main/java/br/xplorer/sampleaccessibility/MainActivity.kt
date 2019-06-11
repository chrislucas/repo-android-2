package br.xplorer.sampleaccessibility

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button_1)
        button.setOnClickListener {
            val intent = Intent(this.applicationContext, SampleListActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(intent)
        }

        val buttonAccessActivityContrastRatio = findViewById<Button>(R.id.button_access_activity_contrast_ratio)
        buttonAccessActivityContrastRatio.setOnClickListener {
            val intent = Intent(this.applicationContext, ContrastRatio::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(intent)
        }
    }
}
