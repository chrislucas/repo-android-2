package com.br.opencamerafromwebview

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.webkit.JsResult
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.opencamerafromwebview.databinding.ActivityOldStyleOpenCameraFromWebViewBinding
import com.br.opencamerafromwebview.databinding.ActivityOpenCameraFromWebViewBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/*
    TODO
    Implementar da maneira antiga os callabcks
    - onRequestPermissionsResult
    - startActivityForResult
    - onActivityResult
 */
class OldStyleOpenCameraFromWebViewActivity : AppCompatActivity() {

    private var imagePathCallback: ValueCallback<Array<Uri>>? = null
    private var cameraImagePath: String? = null


    private val binding: ActivityOldStyleOpenCameraFromWebViewBinding by lazy {
        ActivityOldStyleOpenCameraFromWebViewBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding.run {
            setContentView(root)
            ViewCompat.setOnApplyWindowInsetsListener(main) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }


        checkPermissionsNeeded()
    }

    private fun initWebView() {

        binding.webWiewWithCameraI.run {
            settings.setSupportZoom(true)
            settings.javaScriptEnabled = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            settings.domStorageEnabled = true
            settings.allowContentAccess = true
            webChromeClient = customWebChromeClient()
            webViewClient = customWebViewClient
            loadData(readRaw(R.raw.index), "text/html", "utf-8")
        }
    }

    private val customWebViewClient = object : WebViewClient() {
        val TAG = "WEB_VIEW_CLIENT"

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            Toast.makeText(applicationContext, "Erro ao abrir WebClient", Toast.LENGTH_SHORT)
                .show();
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            Log.i(TAG, "ON_PAGE_STARTED")
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Log.i(TAG, "ON_PAGE_FINISHED")
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            Log.i(TAG, "ON_PAGE_FINISHED ${view?.url}")
            return super.shouldOverrideUrlLoading(view, request)
        }
    }

    private fun customWebChromeClient() = object : WebChromeClient() {

        val TAG = "WEB_CHROME_CLIENT"

        override fun onJsAlert(
            view: WebView?,
            url: String?,
            message: String?,
            result: JsResult?
        ): Boolean {
            /*
                Exemplo de uso do WebChromeClient
                https://gist.github.com/sandeepyohans/ce6c5cc596f693733255c3448a896247
             */
            AlertDialog.Builder(applicationContext)
                .setTitle("")
                .setMessage(message)
                .setPositiveButton(R.string.ok, object : DialogInterface.OnClickListener {
                    override fun onClick(
                        dialog: DialogInterface?,
                        which: Int
                    ) {
                        result?.confirm()
                    }
                })
                .create()
                .show()
            return true
        }

        override fun onPermissionRequest(request: PermissionRequest?) {
            super.onPermissionRequest(request)
            Log.i(TAG, "CAMERA_WEB_VIEW_REQUEST: $request")
        }

        override fun onPermissionRequestCanceled(request: PermissionRequest?) {
            super.onPermissionRequestCanceled(request)
            Log.i(TAG, "onPermissionRequestCanceled: $request")
        }

        override fun onShowFileChooser(
            webView: WebView?,
            filePathCallback: ValueCallback<Array<Uri>>?,
            fileChooserParams: FileChooserParams?
        ): Boolean {
            Log.i(TAG, "onShowFileChooser")
            imagePathCallback?.onReceiveValue(null)
            imagePathCallback = filePathCallback
            val takePictureIntent = createImageCaptureIntent()
            val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
            contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
            contentSelectionIntent.type = INTENT_FILE_TYPE
            val intentArray: Array<Intent?> =
                takePictureIntent?.let { arrayOf(it) } ?: arrayOfNulls(0)

            val chooserIntent =
                Intent.createChooser(contentSelectionIntent, getString(R.string.file_chooser_title))
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray)

            try {

                startActivityForResult(chooserIntent, REQUEST_SELECT_FILE)
            } catch (e: ActivityNotFoundException) {
                imagePathCallback = null
                cameraImagePath = null
                Log.e("LAUNCH", "${getString(R.string.cannot_open_file_chooser_txt)}: $e")
                return false
            }
            return true
        }
    }

    private fun checkPermissionsNeeded() {
        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
        )
        val hasCameraPermission = permissions.map(::checkSelfPermission)
            .all {
                it == PackageManager.PERMISSION_GRANTED
            }


        if (!hasCameraPermission) {
            requestPermissions(permissions, PERMISSIONS_REQUEST_CODE)
        }

        if (hasCameraPermission) {
            initWebView()
            return
        }

        val activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                permissions.values.all { true }.let { flag ->
                    if (flag) {
                        initWebView()
                    }
                }
            }

        activityResultLauncher.launch(permissions)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    this,
                    getString(R.string.camera_permission_granted_txt),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.camera_permission_denied_txt),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (event?.action) {
            KeyEvent.ACTION_DOWN -> {
                if (keyCode == KeyEvent.KEYCODE_BACK && binding.webWiewWithCameraI.canGoBack()) {
                    binding.webWiewWithCameraI.goBack()
                } else {
                    return true
                }
                true
            }
            else -> {
                false
            }
        }
        return false
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        result(requestCode, resultCode, data)
    }

    private fun result(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (requestCode != REQUEST_SELECT_FILE || imagePathCallback == null) {
            return
        }
        result(resultCode, data)
    }

    private fun result(resultCode: Int, data: Intent?) {
        var results: Array<Uri>? = null
        if (resultCode == RESULT_OK) {
            if (data == null) {
                if (cameraImagePath != null) {
                    results = arrayOf(Uri.parse(cameraImagePath))
                }
            } else {
                val dataString = data.dataString
                if (dataString != null) {
                    results = arrayOf(Uri.parse(dataString))
                }
            }
        }
        imagePathCallback?.onReceiveValue(results)
        imagePathCallback = null
    }

    private fun createImageFile(): File? {
        val date = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_S", Locale.getDefault()).format(Date())
        val fileName = "${PHOTO_NAME_POSTFIX}$date"
        return File.createTempFile(
            fileName,
            PHOTO_FORMAT,
            getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )
    }

    private fun createImageCaptureIntent(): Intent? {
        var captureImageIntent: Intent? = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        return if (captureImageIntent?.resolveActivity(packageManager) != null) {
            try {
                var imageFile: File? = createImageFile()
                if (imageFile != null) {
                    val uri = Uri.fromFile(imageFile)
                    cameraImagePath = CAMERA_PHOTO_PATH_POSTFIX + imageFile.absolutePath
                    captureImageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                } else {
                    captureImageIntent = null
                }
                captureImageIntent
            } catch (ex: IOException) {
                Log.e("CAPTURE_IMAGE", "Error on capture image: $ex")
                null
            }
        } else {
            null
        }
    }


    companion object {
        private const val PERMISSIONS_REQUEST_CODE = 113
        private const val REQUEST_SELECT_FILE = 13
        private const val INTENT_FILE_TYPE = "image/*"
        private const val CAMERA_PHOTO_PATH_POSTFIX = "file:"
        private const val PHOTO_NAME_POSTFIX = "jpeg_"
        private const val PHOTO_FORMAT = ".jpg"
    }

    object UploadFile {
        /*
            https://talkjs.com/resources/chat-file-upload-android/
         */
        val html = """
            <!DOCTYPE html>
            <html lang="en">
                <head>
                    <meta http-equiv="content-type" content="text/html; charset=utf-8">
                    <title>Test</title>
                </head>
                <body>
                    <div id="talkjs-container" style="width: 100%; height: 600px">
                        Olá, mundo
                        <input type="file" id="avatar" name="avatar" accept="image/png, image/jpeg" />
                    </div>
                </body>
            </html>
            """.trimIndent()
    }
}