package br.xplorer.driwm.helpers

import android.content.Context
import android.net.wifi.rtt.WifiRttManager
import android.os.Build
import androidx.annotation.RequiresApi

object WifiRtfManagerHelper  {

    @RequiresApi(Build.VERSION_CODES.P)
    fun getWifiRtfManager(context: Context) : WifiRttManager {
        return context.applicationContext
            .getSystemService(Context.WIFI_RTT_RANGING_SERVICE) as WifiRttManager
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun get(context: Context) {
        val wifiRtfManager = getWifiRtfManager(context)
        wifiRtfManager.apply {

        }
    }

}