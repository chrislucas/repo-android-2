package br.xplorer.driwm.helpers

import android.content.Context
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager

object WifiManagerHelper  {

    data class DataNearbyNetwork(val scamResults: List<ScanResult> = listOf()
    , val wifiConfigurations: List<WifiConfiguration> = listOf())

    fun getWifiManager(context: Context) : WifiManager =  context.applicationContext
        .getSystemService(Context.WIFI_SERVICE) as WifiManager


    fun toggleWifiEnabled(context: Context) {
        val wm = getWifiManager(context)
        wm.apply {
            isWifiEnabled = ! isWifiEnabled
        }
    }

    fun isEnable(context: Context):  Boolean = getWifiManager(context).isWifiEnabled


}