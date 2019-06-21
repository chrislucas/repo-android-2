package br.xplorer.driwm.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import br.xplorer.driwm.ObservableWifiReceiver
import br.xplorer.driwm.helpers.WifiManagerHelper

class WifiBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        when(action) {
            WifiManager.SCAN_RESULTS_AVAILABLE_ACTION -> {
                ObservableWifiReceiver.INSTANCE.updateValue(true)
            }
        }
    }
}
