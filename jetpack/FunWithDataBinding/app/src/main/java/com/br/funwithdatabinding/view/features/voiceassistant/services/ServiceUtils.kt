package com.br.funwithdatabinding.view.features.voiceassistant.services

import android.app.ActivityManager
import android.content.Context


fun Context.isMyServiceRunning(serviceClass: Class<*>): Boolean {
    val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

    for (service in manager.getRunningServices(20)) {
        if (serviceClass.name.equals(service.service.className)) {
            return true
        }
    }

    return false
}