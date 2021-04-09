package com.br.samples.api.rqpermission.views

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.br.samples.api.ext.R
import com.br.samples.api.rqpermission.permission.contracts.multiplesContracts

class AndroidXRequestMultiplePermissionsApiActivity : AppCompatActivity() {


    companion object {
        const val REQUEST_ACCESS = 0x32
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        val permissions =
            arrayOf(
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.CAMERA,
            )

        // abrindo o dialog para pedir permissao
        multiplesContracts(permissions)
        /**
         * implementacao de {@link ActivityResultCallback}
         * */
        { mapResult ->
            if (mapResult.all { (_, v) -> v }) {
                // Todas as permissoes solicitadas fora dadas
                Log.i("PERMISSION_GRANTED", "$permissions")
            } else {
                // se alguma nao foi
                Log.e("PERMISSION_DENIED", "$permissions")
            }
        }
    }
}