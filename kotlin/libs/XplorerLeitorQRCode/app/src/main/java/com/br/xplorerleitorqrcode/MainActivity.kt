package com.br.xplorerleitorqrcode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.br.wrapbarcodereader.ChooseQrCodeReader

class MainActivity : AppCompatActivity() {


    private val tvContent: TextView by lazy { findViewById(R.id.tv_content) }

    private val btChooseQrCodeReader: Button by lazy {
        findViewById(R.id.bt_open_activity_choose_barcode_reader)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btChooseQrCodeReader.setOnClickListener {
            startActivityForResult(
                Intent(this, ChooseQrCodeReader::class.java),
                ChooseQrCodeReader.REQUEST_CODE_ACTIVITY
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                ChooseQrCodeReader.REQUEST_CODE_ACTIVITY -> {
                    val content = data?.getStringExtra(ChooseQrCodeReader.RESULT_DATA) ?: ""
                    Log.i("BARCODE_READER", content)
                    tvContent.text = content
                }
            }
        }
    }
}