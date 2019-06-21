package br.xplorer.driwm

import android.Manifest
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.xplorer.driwm.helpers.WifiManagerHelper
import br.xplorer.driwm.receiver.WifiBroadcastReceiver
import java.util.*

class MainActivity :  AppCompatActivity(), Observer {

    override fun update(observable: Observable?, data: Any?) {
        if (data != null && data is Boolean && data) {

            val wm = WifiManagerHelper.getWifiManager(this)
            val scanResults = wm.scanResults
            val configuredNetworks = wm.configuredNetworks
            val connectionInfo = wm.connectionInfo

            connectionInfo.apply {
                Log.i("STATUS_CONNECTION_INFO","${this.bssid}, ${this.ssid}")
            }

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

            configuredNetworks.forEach {
                it.apply {
                    Log.i("STATUS_WIFI_CONFIG", "${this.BSSID}. ${this.SSID}, ${this.status}")
                }
            }
        }
    }

    companion object {
        const val REQUEST_PERMISSIONS = 0x0f
    }

    lateinit var wifiBroadcastReceiver : WifiBroadcastReceiver

    private var flagStartScanNetwork = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flagStartScanNetwork = requestPermissionNeeded()
        wifiBroadcastReceiver = WifiBroadcastReceiver()
    }

    private fun requestPermissionNeeded() : Boolean {
        val permissions = arrayOf(Manifest.permission.INTERNET
            ,Manifest.permission.ACCESS_WIFI_STATE
            ,Manifest.permission.CHANGE_WIFI_STATE
            ,Manifest.permission.ACCESS_NETWORK_STATE
            ,Manifest.permission.ACCESS_FINE_LOCATION
            ,Manifest.permission.ACCESS_COARSE_LOCATION
            ,Manifest.permission.READ_PHONE_STATE).filter {
                ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_DENIED
            }.toTypedArray()

        if (permissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_PERMISSIONS) {
            flagStartScanNetwork = true
            for(i in 0 until permissions.size) {
                if(permissions[i] == Manifest.permission.CHANGE_WIFI_STATE && grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    flagStartScanNetwork = false
                    break
                }
            }
            startReceiverScan()
        }
    }


    override fun onResume() {
        super.onResume()
        startReceiverScan()
    }

    private fun startReceiverScan() {
        if(flagStartScanNetwork) {
            this.registerReceiver(wifiBroadcastReceiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
            //WifiManagerHelper.getWifiManager(this).startScan()
        }
    }

    private fun stopReceiverScan() {
        if(flagStartScanNetwork) {
            this.unregisterReceiver(wifiBroadcastReceiver)
        }
    }

    override fun onPause() {
        super.onPause()
        stopReceiverScan()
    }
}
