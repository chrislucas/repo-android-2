package com.br.justcomposelabs.tutorial.google.lifecycle.receiveupdatelocation.base

import android.app.Service
import android.content.Intent
import android.os.IBinder

/*
    LocationManager
    https://developer.android.com/reference/android/location/LocationManager
    
    https://codelabs.developers.google.com/codelabs/while-in-use-location#2
    https://github.com/android/codelab-while-in-use-location/blob/master/base/src/main/java/com/example/android/whileinuselocation/ForegroundOnlyLocationService.kt
 */
class ForegroundOnlyLocationService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}