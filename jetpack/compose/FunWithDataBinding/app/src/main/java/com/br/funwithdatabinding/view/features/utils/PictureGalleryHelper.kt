package com.br.funwithdatabinding.view.features.utils

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PictureGalleryHelper(private val context: Context) {

    fun saveOutputImageFile(captureImageIntent: Intent) {
        /*
            Se utilizar MediaStore.EXTRA_OUTPUT ao tirar a foto o arquivo vai
            ser escrito no diretorio definido e Intent.data retorna null
         */
        createImageOnDirectoryPics()?.let { imageFile ->
            captureImageIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile))
        }
    }

    fun saveOutputImageUri(captureImageIntent: Intent) {
        createImageExternalContentUri()?.let { imageUri ->
            captureImageIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        }
    }

    fun createImageOnDirectoryPics(
        photoNamePrefix: String = PHOTO_NAME_PREFIX,
        photoFormat: String = PHOTO_FORMAT
    ): File? {
        val date = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_S", Locale.getDefault()).format(Date())
        val fileName = "${photoNamePrefix}$date$photoFormat"
        val environment = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        )
        return File(environment, fileName)
    }

    fun createImageExternalContentUri(
        photoNamePrefix: String = PHOTO_NAME_PREFIX,
        photoFormat: String = PHOTO_FORMAT
    ): Uri? {
        val contentValues = ContentValues()
        val date = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_S", Locale.getDefault()).format(Date())
        val fileName = "${photoNamePrefix}$date$photoFormat"
        contentValues.put(MediaStore.Images.Media.TITLE, fileName);
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "From_Camera");
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
        return context.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )
    }

    companion object {
        private const val PHOTO_NAME_PREFIX = "photo_"
        private const val PHOTO_FORMAT = ".jpeg"

        fun createImageCaptureIntent(context: Context): Intent? {
            var captureImageIntent: Intent? = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            return if (captureImageIntent?.resolveActivity(context.packageManager) != null) {
                try {
                    captureImageIntent
                } catch (ex: IOException) {
                    Timber.tag("CAPTURE_IMAGE").e("Error on capture image: $ex")
                    null
                }
            } else {
                null
            }
        }
    }
}