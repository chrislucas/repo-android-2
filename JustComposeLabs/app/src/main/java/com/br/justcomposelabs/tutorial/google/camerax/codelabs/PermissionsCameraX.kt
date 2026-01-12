package com.br.justcomposelabs.tutorial.google.camerax.codelabs

import android.content.Context
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

/*
    CameraX overview
    https://developer.android.com/media/camera/camerax
    https://developer.android.com/codelabs/camerax-getting-started#2
 */

private fun AppCompatActivity.activityResultLauncher(startCamera: () -> Unit) {
    registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions())
    { permissions ->
        // Handle Permission granted/rejected
        var permissionGranted = true
        permissions.entries.forEach {
            /*
            if (it.key in REQUIRED_PERMISSIONS && it.value == false)
                permissionGranted = false

             */

        }
        if (!permissionGranted) {
            Toast.makeText(baseContext,
                "Permission request denied",
                Toast.LENGTH_SHORT).show()
        } else {
            startCamera()
        }
    }
}
