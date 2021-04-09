package com.br.samples.api.ext

import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.IntRange
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.FragmentActivity


fun FragmentActivity.hasUserAlreadyGrantedPermission(permission: String) =
    checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED


@RequiresApi(Build.VERSION_CODES.M)
fun FragmentActivity.shouldShowRqPermissionRationale(permission: String) =
    shouldShowRequestPermissionRationale(permission)


fun FragmentActivity.requestPermissionsCompat(
    permissions: Array<String>,
    @IntRange(from = 0) requestCode: Int
) =
    ActivityCompat.requestPermissions(this, permissions, requestCode)