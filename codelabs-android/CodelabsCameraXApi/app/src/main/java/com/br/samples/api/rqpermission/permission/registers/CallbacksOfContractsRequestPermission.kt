package com.br.samples.api.rqpermission.permission.registers

import androidx.activity.result.ActivityResultCallback
import com.br.samples.api.rqpermission.permission.registers.CallbacksOfContractsRequestPermission.allPermissionWereGranted
import com.br.samples.api.rqpermission.permission.registers.CallbacksOfContractsRequestPermission.getActivityResultCallbackMultipleRequests

object CallbacksOfContractsRequestPermission {

    fun <R> getActivityResultCallbackSingleRequest(fn: (Boolean) -> R): ActivityResultCallback<Boolean> {
        return ActivityResultCallback {
            fn(it)
        }
    }

    fun <R> getActivityResultCallbackMultipleRequests(fn: (Map<String, Boolean>) -> R):
            ActivityResultCallback<Map<String, Boolean>> {
        return ActivityResultCallback {
            fn(it)
        }
    }

    fun allPermissionWereGranted(permissions: Map<String, Boolean>) =
        permissions.all { (_, state) -> state }

}


fun main() {
    getActivityResultCallbackMultipleRequests(::allPermissionWereGranted)
}