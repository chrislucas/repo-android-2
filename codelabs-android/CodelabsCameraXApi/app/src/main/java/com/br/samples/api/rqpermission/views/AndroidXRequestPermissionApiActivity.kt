package com.br.samples.api.rqpermission.views

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.br.samples.api.ext.R


/**
 * https://developer.android.com/training/permissions/requesting
 * https://developer.android.com/training/permissions/requesting#allow-system-manage-request-code
 * */

class AndroidXRequestPermissionApiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_x_request_permission_api)

        /**
         * 1) no metodo de inicializacao da activity ou fragment chame o metodo
         * registerForActivityResult(ActivityResultContract<I, O> contract
         * , ActivityResultCallback<O> callback)
         * e passe uma instancia de ActivityResultContract e uma implementacao
         * de ActivityResultCallback
         *
         *      Para pedir permissao a algum recurso do dispositivo ja existe um contrato/implementacao
         *      definido na SDK do android ActivityResultContracts.RequestPermission()
         *
         * */

        val result: ActivityResultLauncher<String> =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    // DO SOMETHING
                    Log.i("PERMISSIONS_GRANTED", "$isGranted")
                } else {
                    Log.e("PERMISSIONS_DENIED", "$isGranted")
                }
            }


        result.launch(Manifest.permission.ACCESS_WIFI_STATE)
    }
}