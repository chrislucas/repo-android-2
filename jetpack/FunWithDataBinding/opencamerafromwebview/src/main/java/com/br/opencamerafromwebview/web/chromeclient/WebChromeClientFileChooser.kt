package com.br.opencamerafromwebview.web.chromeclient

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.webkit.JsResult
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import com.br.opencamerafromwebview.R
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WebChromeClientFileChooser(
    private val context: Context,
    private val registerForActivityResult: ActivityResultLauncher<Intent>
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

    override fun onJsAlert(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        AlertDialog.Builder(context)
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
            .create().create()
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
        Log.i(TAG, "onShowFileChooser: $fileChooserParams")
        return webView?.let { view ->
            imagePathCallback?.onReceiveValue(null)
            imagePathCallback = filePathCallback
            val takePictureIntent = createImageCaptureIntent()
            val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
            contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
            // permite selecionar multiplos arqvuivos
            contentSelectionIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            contentSelectionIntent.type = INTENT_FILE_TYPE
            val intentArray: Array<Intent?> = takePictureIntent?.let {
                arrayOf(it)
            } ?: arrayOfNulls(0)
            val chooserIntent = Intent.createChooser(
                contentSelectionIntent,
                context.getString(R.string.file_chooser_title)
            )
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray)
            try {
                registerForActivityResult.launch(chooserIntent)
                true
            } catch (e: ActivityNotFoundException) {
                imagePathCallback = null
                cameraImagePath = null
                Log.e(
                    TAG, "${context.getString(R.string.cannot_open_file_chooser_txt)}: $e"
                )
                false
            }
        } == true
    }


    private fun createImageFile(): File? {
        /*
            //return File(Environment.getExternalStorageDirectory(), fileName + PHOTO_FORMAT)
            //return File(context.externalCacheDir, fileName + PHOTO_FORMAT)
            //return File.createTempFile(fileName, PHOTO_FORMAT, getExternalFilesDir(Environment.DIRECTORY_PICTURES))
         */
        val date = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_S", Locale.getDefault()).format(Date())
        val fileName = "${PHOTO_NAME_POSTFIX}$date$PHOTO_FORMAT"
        return File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            fileName
        )
    }

    private fun createImageCaptureIntent(): Intent? {
        var captureImageIntent: Intent? = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        return if (captureImageIntent?.resolveActivity(context.packageManager) != null) {
            try {
                var imageFile: File? = createImageFile()
                if (imageFile != null) {
                    cameraImagePath = createFilePath(imageFile)
                    captureImageIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile))
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

    private fun createFilePath(file: File): String = CAMERA_PHOTO_PATH_POSTFIX + file.absolutePath
}