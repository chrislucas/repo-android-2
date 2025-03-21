package com.br.funwithdatabinding.view.features.webview.jsinterface

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R
import com.br.funwithdatabinding.databinding.ActivityLaunchIntentFromJsBinding
import com.br.funwithdatabinding.view.features.utils.PictureGalleryHelper.Companion.createImageCaptureIntent
import com.br.opencamerafromwebview.readRaw
import timber.log.Timber
import java.io.IOException

class LaunchIntentFromJsActivity : AppCompatActivity(), CameraAction {

    private val bindView: ActivityLaunchIntentFromJsBinding by lazy {
        ActivityLaunchIntentFromJsBinding.inflate(layoutInflater)
    }

    private val registerTakePictureGetResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            getTakePictureResult(result.resultCode, result.data)
        }

    private val registerCallbackOpenCamera = RegisterCallbackOpenCamera(
        this,
        registerTakePictureGetResult
    )

    val getContent = registerForActivityResult(GetContent()) { uri: Uri? ->
        println(uri)
    }

    private fun getTakePictureResult(resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            data?.let {
                val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.extras?.getParcelable("data", Bitmap::class.java)
                } else {
                    it.extras?.getParcelable<Bitmap>("data")
                }
                bindView.photo.setImageBitmap(bitmap)
            }
        }
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
        }
        askForPermission()
    }

    override fun openCamera() {
        registerCallbackOpenCamera.openCamera()
    }

    private fun initWebView() {
        bindView.webViewOpenCameraOrGallery.run {
            settings.setSupportZoom(true)
            settings.javaScriptEnabled = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            settings.domStorageEnabled = true
            settings.allowContentAccess = true
            addJavascriptInterface(
                BridgeJsLaunchCameraIntent(this@LaunchIntentFromJsActivity),
                "AndroidWebView"
            )
            loadData(readRaw(com.br.mylibrary.R.raw.camera_or_gallery), "text/html", "utf-8")
        }
    }

    fun askForPermission() {
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
            initWebView()
            return
        }

        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.values.all { it }.let { flag ->
                if (flag) {
                    initWebView()
                }
            }
        }.launch(allPermissions)
    }

    override fun getContext(): Context = this

    override fun getContent() {
        getContent.launch(INTENT_FILE_TYPE)
    }

    companion object {
        private const val INTENT_FILE_TYPE = "image/*"
    }
}


interface CameraAction {
    fun getContext(): Context
    fun getContent()
    fun openCamera()
}


private class BridgeJsLaunchCameraIntent(private val cameraAction: CameraAction) {

    private val context = cameraAction.getContext()

    companion object {
        private const val INTENT_FILE_TYPE = "image/*"
    }

    @JavascriptInterface
    fun launchGallery() {
        Toast.makeText(context, "Galeria", Toast.LENGTH_LONG).show()
        cameraAction.getContent()
    }


    @JavascriptInterface
    fun launchCamera() {
        Toast.makeText(context, "Camera", Toast.LENGTH_LONG).show()
        cameraAction.openCamera()
    }
}

private class RegisterCallbackOpenCamera(
    private val context: Context,
    private val registerForActivityResult: ActivityResultLauncher<Intent>
) {
    fun openCamera(): Boolean {
        return try {
            createImageCaptureIntent(context)?.let(registerForActivityResult::launch)
            true
        } catch (e: ActivityNotFoundException) {
            Timber.tag(TAG).e("${context.getString(R.string.cannot_open_camera)}: $e")
            false
        }
    }

    companion object {
        private const val TAG = "WEB_CHROME_CLIENT"
    }
}


