package com.br.opencamerafromwebview.tutorialii

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
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
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

class AnotherFileUploadWebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_another_file_upload_web_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

class WebChromeFileChooserIntent(
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
            try {
                createTakePictureIntent()?.let { intent ->
                    registerForActivityResult.launch(intent)
                    true
                } == true

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

    private fun createTakePictureIntent() = createImageCaptureIntent()?.also { takePictureIntent ->
        val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
        contentSelectionIntent.type = INTENT_FILE_TYPE
        val intentArray: Array<Intent?> = takePictureIntent.let { arrayOf(it) }
        val chooserIntent = Intent.createChooser(contentSelectionIntent, "Escolha um arquivo")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray)
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