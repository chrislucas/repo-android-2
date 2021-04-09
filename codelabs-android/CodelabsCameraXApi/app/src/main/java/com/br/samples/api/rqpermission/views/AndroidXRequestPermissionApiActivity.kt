package com.br.samples.api.rqpermission.views

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.br.samples.api.ext.R
import com.br.samples.api.rqpermission.permission.contracts.singlePermissionContract


/**
 * https://developer.android.com/training/permissions/requesting
 * https://developer.android.com/training/permissions/requesting#allow-system-manage-request-code
 * */

class AndroidXRequestPermissionApiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_x_request_permission_api)

        singlePermissionContract(Manifest.permission.ACCESS_WIFI_STATE) { isGranted ->
            if (isGranted) {
                // DO SOMETHING
                Log.i("PERMISSIONS_GRANTED", "$isGranted")
            } else {
                Log.e("PERMISSIONS_DENIED", "$isGranted")
            }
        }
    }
}