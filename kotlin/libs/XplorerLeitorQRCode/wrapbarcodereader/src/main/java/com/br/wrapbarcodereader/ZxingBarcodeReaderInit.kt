package com.br.wrapbarcodereader

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

class ZxingBarcodeReaderInit(private val activity: AppCompatActivity) : BarcodeReaderInit {
    override fun init() {
        IntentIntegrator(activity).initiateScan()
    }
}