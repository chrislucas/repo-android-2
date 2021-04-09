package com.br.samples.api.calllogs

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.br.samples.api.ext.R

import com.br.samples.api.rqpermission.registers.CallbacksOfContractsRequestPermission.getActivityResultCallbackSingleRequest


class ReadCallLogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_call_log)
    }

    private fun requestPermissionForReadCallLog() {

        fun predicate(data: Boolean): Unit {
            Log.v("LOG", "$data")
        }

        val result = registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
            getActivityResultCallbackSingleRequest(::predicate)
        )

        result.launch(Manifest.permission.READ_CALL_LOG)
    }
}