package com.br.wrapbarcodereader

import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

class ZxingBarcodeScannerInit(private val activity: AppCompatActivity) : BarcodeScannerInit {
    override fun init() {
        IntentIntegrator(activity).initiateScan()
    }
}