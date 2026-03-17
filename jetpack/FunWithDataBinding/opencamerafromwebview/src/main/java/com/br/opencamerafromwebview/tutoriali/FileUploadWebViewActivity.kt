package com.br.opencamerafromwebview.tutoriali

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.opencamerafromwebview.CaptureImage
import com.br.opencamerafromwebview.R
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

interface IntentCaptureImage {
    fun createIntentCaptureImage(): Intent?
}

class WebChromeFileChooserClientII(
    private val registerForActivityResult: ActivityResultLauncher<Intent>,
    private val captureImage: CaptureImage
) : WebChromeClient() {

    companion object {
        private const val INTENT_FILE_TYPE = "image/*"
        private const val TAG = "WEB_CHROME_CLIENT"
        private const val CAMERA_PHOTO_PATH_POSTFIX = "file:"
        private const val PHOTO_NAME_POSTFIX = "web_view_photo_"
        private const val PHOTO_FORMAT = ".jpeg"
    }

    var imagePathCallback: ValueCallback<Array<Uri>>? = null
    var cameraImagePath: String? = null

    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        Log.i(TAG, "onShowFileChooser: $fileChooserParams")
        return webView?.let { view ->
            imagePathCallback?.onReceiveValue(null)
            imagePathCallback = filePathCallback
            val takePictureIntent = createImageCaptureIntent()
            val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
            contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
            contentSelectionIntent.type = INTENT_FILE_TYPE
            val intentArray: Array<Intent?> = takePictureIntent?.let {
                arrayOf(it)
            } ?: arrayOfNulls(0)
            val chooserIntent = Intent.createChooser(
                contentSelectionIntent,
                "Escolha um arquivo"
            )
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray)
            try {
                registerForActivityResult.launch(chooserIntent)
                true
            } catch (e: ActivityNotFoundException) {
                imagePathCallback = null
                cameraImagePath = null
                Log.e(TAG, "$e")
                false
            }
        } == true
    }

    private fun createImageFile(): File? {
        val date = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_S", Locale.getDefault()).format(Date())
        val fileName = "${PHOTO_NAME_POSTFIX}$date$PHOTO_FORMAT"
        return File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            fileName
        )
    }

    private fun createImageCaptureIntent(): Intent? {
        return captureImage.createIntentCaptureImage()?.run {
            try {
                createImageFile()?.let { file ->
                    putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file))
                    cameraImagePath = createFilePath(file)
                }
                this
            } catch (ex: IOException) {
                Log.e("CAPTURE_IMAGE", "Error on capture image: $ex")
                null
            }
        }
    }

    private fun createFilePath(file: File): String = CAMERA_PHOTO_PATH_POSTFIX + file.absolutePath
}

class FileUploadWebViewActivity : AppCompatActivity(), CaptureImage {

    private val webChromeFileChooserClientII = WebChromeFileChooserClientII(
        registerForActivityResult(StartActivityForResult()) { result ->
            callbackRegisterForActivityResult(
                result.resultCode,
                result.data,
            )
        },
        this@FileUploadWebViewActivity
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_upload_web_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val view = findViewById<WebView>(R.id.file_upload)
        view.run {
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

            loadData(html, "text/html", "utf-8")
            webChromeClient = webChromeFileChooserClientII
        }
    }

    private fun callbackRegisterForActivityResult(
        resultCode: Int,
        data: Intent?
    ) {
        var results: Array<Uri>? = null
        if (resultCode == RESULT_OK) {
            if (data != null) {
                val dataString = data.dataString
                if (dataString != null) {
                    results = arrayOf(Uri.parse(dataString))
                }
            } else {
                if (webChromeFileChooserClientII.cameraImagePath != null) {
                    results = arrayOf(Uri.parse(webChromeFileChooserClientII.cameraImagePath))
                }
            }
        }
        results?.get(0)?.let { uri ->
            println(uri)
        }
        webChromeFileChooserClientII.imagePathCallback?.onReceiveValue(results)
        webChromeFileChooserClientII.imagePathCallback = null
    }

    override fun createIntentCaptureImage(): Intent? {
        return with(Intent(MediaStore.ACTION_IMAGE_CAPTURE)) {
            if (resolveActivity(packageManager) != null) {
                this
            } else {
                null
            }
        }
    }
}


