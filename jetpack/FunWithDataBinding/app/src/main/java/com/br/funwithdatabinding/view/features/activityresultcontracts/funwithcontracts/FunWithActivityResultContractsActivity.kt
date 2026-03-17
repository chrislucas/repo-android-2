package com.br.funwithdatabinding.view.features.activityresultcontracts.funwithcontracts

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.databinding.ActivityFunWithResultContractsBinding
import com.br.funwithdatabinding.view.features.utils.PictureGalleryHelper
import com.br.funwithdatabinding.view.features.utils.getPickVisualMediaRequestContract

/**
 *
TODO criar uma lista para explorar os contratos padroes e os contratos customizados
do arqiuivo
 ** @see com.br.funwithdatabinding.view.features.utils.SelectMultiplesPicsContract
- https://developer.android.com/reference/androidx/activity/result/contract/ActivityResultContracts
- https://developer.android.com/training/basics/intents/result
- https://medium.com/captech-corner/android-activity-results-contracts-54b2016a84db
Photo picker
https://developer.android.com/training/data-storage/shared/photopicker

 */
class FunWithActivityResultContractsActivity : AppCompatActivity() {


    private val bindView: ActivityFunWithResultContractsBinding by lazy {
        ActivityFunWithResultContractsBinding.inflate(layoutInflater)
    }

    private val pickVisualMediaRequestContract = getPickVisualMediaRequestContract(
        3,
        ::onSuccess,
        ::onError
    )


    private val registerTakePictureGetResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            getTakePictureResult(result.resultCode, result.data)
        }


    private fun getTakePictureResult(resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            data?.let {
                val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.extras?.getParcelable("data", Bitmap::class.java)
                } else {
                    it.extras?.getParcelable<Bitmap>("data")
                }

                println(bitmap)
            }
        }
    }


    private fun onSuccess(uris: List<Uri>) {
        // TODO implementar algo
        println(uris)
    }

    private fun onError() {
        // TODO implementar algo
        println("Error")
    }

    private fun askForPermission() {
        val allPermissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
        )
        val hasCameraPermission = allPermissions.map(::checkSelfPermission)
            .all {
                it == PackageManager.PERMISSION_GRANTED
            }
        if (hasCameraPermission) {
            return
        }

        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.values.all { it }.let { flag ->
                if (!flag) {
                    // TODO pedir novamente
                }
            }
        }.launch(allPermissions)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bindView.run {
            setContentView(root)

            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            askForPermission()


            btnOpenGalleryWithLimit.setOnClickListener {
                pickVisualMediaRequestContract.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))

            }
        }
    }

    private fun test() {
        PictureGalleryHelper.createImageCaptureIntent(
            this@FunWithActivityResultContractsActivity.baseContext
        )?.let(registerTakePictureGetResult::launch)
    }
}