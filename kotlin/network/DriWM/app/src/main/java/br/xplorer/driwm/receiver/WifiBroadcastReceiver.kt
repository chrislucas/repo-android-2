package br.xplorer.driwm.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.util.Log
import br.xplorer.driwm.ObservableWifiReceiver
import br.xplorer.driwm.helpers.WifiManagerHelper

class WifiBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action) {
            WifiManager.SCAN_RESULTS_AVAILABLE_ACTION -> {
                ObservableWifiReceiver.INSTANCE.updateValue(true)
            }
            WifiManager.WIFI_STATE_CHANGED_ACTION -> {
                val extras = intent.extras
                val wm = WifiManagerHelper.getWifiManager(context)
                val wifiInfo = wm.connectionInfo
                Log.i("WIFI_STATE_C_ACTION", "${WifiManagerHelper.mapStateWifi[wm.wifiState]}, $wifiInfo")
            }
            WifiManager.NETWORK_STATE_CHANGED_ACTION -> {
                val extras = intent.extras
            }
        }
    }
}
