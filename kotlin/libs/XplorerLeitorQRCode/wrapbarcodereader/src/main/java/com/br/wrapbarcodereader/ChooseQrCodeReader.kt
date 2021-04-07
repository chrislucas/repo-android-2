package com.br.wrapbarcodereader

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

class ChooseQrCodeReader : AppCompatActivity() {

    private val optionsBarcodeReader: Spinner by lazy { findViewById(R.id.spn_qrcode_readers) }

    private val btcConfirmOptionChosen: Button by lazy { findViewById(R.id.btn_confirm_qrcode_reader) }

    private val barcodeReadersInitializer: Array<BarcodeReaderInit> =
        arrayOf(GoogleMLKitInit(), ZxingBarcodeReaderInit(this))

    private var barcodeReaderInit: BarcodeReaderInit? = null

    companion object {
        const val RESULT_DATA = "RESULT_DATA"
        const val REQUEST_CODE_ACTIVITY = 1 shl 8
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_qr_code_reader)
        initSpinnerOptionsBarcodeReader()
        initListenerOnConfirmButton()
    }


    private fun initSpinnerOptionsBarcodeReader() {
        optionsBarcodeReader.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // opcao 0 - selecione
                barcodeReaderInit = if (position > 0) {
                    barcodeReadersInitializer[position - 1]
                } else {
                    null
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // NOTHING
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (intentResult != null) {
            val contents = intentResult.contents
            contents?.let { content ->
                val intent = Intent()
                intent.putExtra(RESULT_DATA, content)
                setResult(RESULT_OK, intent)
            } ?: setResult(RESULT_CANCELED)
            finish()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun initListenerOnConfirmButton() {
        btcConfirmOptionChosen.setOnClickListener {
            barcodeReaderInit?.init() ?: askingForUserChooseABarcodeReader()
        }
    }

    private fun askingForUserChooseABarcodeReader() {
        Toast.makeText(this, "Escolha um leitor de QRCode", Toast.LENGTH_SHORT).show()
    }
}