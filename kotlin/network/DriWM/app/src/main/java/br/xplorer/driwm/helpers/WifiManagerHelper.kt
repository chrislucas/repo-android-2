package br.xplorer.driwm.helpers

import android.content.Context
import android.content.Intent
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import android.net.wifi.SupplicantState



object WifiManagerHelper  {

    @JvmStatic
    val mapStateWifi = mapOf(WifiManager.WIFI_STATE_DISABLING to "WIFI_STATE_DISABLING"
        , WifiManager.WIFI_STATE_ENABLED to "WIFI_STATE_ENABLED"
        , WifiManager.WIFI_STATE_ENABLING to "WIFI_STATE_ENABLING"
        , WifiManager.WIFI_STATE_UNKNOWN to "WIFI_STATE_UNKNOWN")



    data class InfoScanNetwork(val scanResultWifi: ScanResult) {
        override fun toString(): String {
            return "BSSID: $scanResultWifi"
        }
    }

    @JvmStatic
    fun WifiInfo.logi(tag: String, context: Context) {
        run {
            Log.i(tag, "${this.supplicantState}")
            Log.i(tag, "${this.networkId}")
            Log.i(tag, "${this.linkSpeed}")
            Log.i(tag, "${this.ipAddress}")
        }
    }


    @JvmStatic
    fun openWifiSettings(context: Context) {
        context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
    }

    @JvmStatic
    fun openWirelessSettings(context: Context) {
        context.startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
    }

    @JvmStatic
    fun getWifiSupplicantState(context: Context) : SupplicantState  = getWifiInfo(context).run { supplicantState }

    @JvmStatic
    fun getWifiInfo(context: Context) : WifiInfo  = getWifiManager(context).connectionInfo

    @JvmStatic
    fun enableWifi(context: Context) {
        getWifiManager(context)
            .takeIf { ! it.isWifiEnabled }
            .apply { this?.isWifiEnabled = true }
    }



    fun startScan(context: Context) : Boolean {
        enableWifi(context)
        return getWifiManager(context).startScan()
    }

    @JvmStatic
    fun logScanResult(scanResults: List<ScanResult>) {
        scanResults.forEach {
            it.apply {
                val str =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        "${this.SSID}, ${this.BSSID}, ${this.level}, ${this.operatorFriendlyName}"
                    } else {
                        "${this.level}"
                    }
                Log.i("STATUS_SCAN_RESULT", str)
            }
        }
    }

    @JvmStatic
    fun logWifiConfiguration(configuredNetworks: List<WifiConfiguration>) {
        configuredNetworks.forEach {
            it.apply {
                Log.i("STATUS_WIFI_CONFIG", "${this.BSSID}. ${this.SSID}, ${this.status}")
            }
        }
    }

    @JvmStatic
    fun logWifiInfo(connectionInfo: WifiInfo) {
        connectionInfo.apply {
            Log.i("STATUS_CONNECTION_INFO","${this.bssid}, ${this.ssid}")
        }
    }

    @JvmStatic
    fun getWifiManager(context: Context) : WifiManager =  context.applicationContext
        .getSystemService(Context.WIFI_SERVICE) as WifiManager

    @JvmStatic
    fun resetConfigWifi(context: Context, networkId: Int) : Boolean = getWifiManager(context).removeNetwork(networkId)

    @JvmStatic
    fun toggleWifiEnabled(context: Context) {
        val wm = getWifiManager(context)
        wm.apply {
            isWifiEnabled = ! isWifiEnabled
        }
    }

    fun isEnable(context: Context):  Boolean = getWifiManager(context).isWifiEnabled


}