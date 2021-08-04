package com.br.samples.api.camerax

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.br.samples.api.ext.R
import com.br.samples.api.ext.hasUserAlreadyGrantedPermission
import com.br.samples.api.ext.requestPermissionsCompat
import com.br.samples.api.rqpermission.views.AndroidXRequestMultiplePermissionsApiActivity
import com.br.samples.api.rqpermission.views.AndroidXRequestPermissionApiActivity

class MainActivity : AppCompatActivity() {


    private val btnOpenActivityReqPermAndroidXApi: Button by lazy {
        findViewById(R.id.btn_api_rq_perm_androidx)
    }

    private val btnOpenActivityReqMultiplePermAndroidXApi: Button by lazy {
        findViewById(R.id.btn_api_rq_multiple_perm_androidx)
    }

    companion object {
        const val REQUEST_CODE_PERMISSION = 0xff
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (hasUserAlreadyGrantedPermission(Manifest.permission.CAMERA)) {
            startCamera()
        } else {
            requestAccessCamera()
        }

        initButtonOpenActivitySampleRequestPermissionApiAndroidX()
    }

    private fun requestAccessCamera() {
        val cameraPermission = Manifest.permission.CAMERA
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(cameraPermission), REQUEST_CODE_PERMISSION)
        } else {
            requestPermissionsCompat(arrayOf(cameraPermission), REQUEST_CODE_PERMISSION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (allPermissionsGranted(grantResults)) {
                startCamera()
            }
        }
    }

    private fun initButtonOpenActivitySampleRequestPermissionApiAndroidX() {
        btnOpenActivityReqPermAndroidXApi.setOnClickListener {
            startActivity(Intent(this, AndroidXRequestPermissionApiActivity::class.java))
        }

        btnOpenActivityReqMultiplePermAndroidXApi.setOnClickListener {
            startActivity(Intent(this, AndroidXRequestMultiplePermissionsApiActivity::class.java))
        }
    }

    private fun startCamera() {

    }

    private fun allPermissionsGranted(grantResults: IntArray): Boolean =
        grantResults.toTypedArray().all { it == PackageManager.PERMISSION_GRANTED }

}