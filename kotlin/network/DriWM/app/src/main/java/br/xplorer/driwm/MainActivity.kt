package br.xplorer.driwm

import android.Manifest
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.xplorer.driwm.adapters.rc.OnItemLongClickListener
import br.xplorer.driwm.adapters.rc.RcAdapterNearbyNetwork
import br.xplorer.driwm.helpers.WifiManagerHelper
import br.xplorer.driwm.receiver.WifiBroadcastReceiver
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import java.util.*
import kotlin.math.abs

class MainActivity :  BaseActivity()
    , Observer
    , OnItemLongClickListener<WifiManagerHelper.InfoScanNetwork> {

    companion object {
        const val REQUEST_PERMISSIONS = 0x0f
    }

    private lateinit var wifiBroadcastReceiver : WifiBroadcastReceiver
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable


    private lateinit var rcAdapter: RcAdapterNearbyNetwork
    private val listWifi = mutableListOf<WifiManagerHelper.InfoScanNetwork>()

    private var flagStartScanNetwork = false
    private var isRegisterWifiReceiver = false
    private var appBarExpanded = false

    override fun onLongClick(data: WifiManagerHelper.InfoScanNetwork) : Boolean {
        Toast.makeText(this, "$data", Toast.LENGTH_LONG).show()
        return true
    }

    override fun onItemClick(item: WifiManagerHelper.InfoScanNetwork) {
        Toast.makeText(this, "$item", Toast.LENGTH_LONG).show()
    }

    override fun update(observable: Observable?, data: Any?) {
        if (data != null && data is Boolean && data) {
            val wm = WifiManagerHelper.getWifiManager(this)
            val results = wm.scanResults
            val it = listWifi.iterator()
            while (it.hasNext()) {
                it.remove()
                it.next()
            }
            results.forEach {
                scanResult -> listWifi.add(WifiManagerHelper.InfoScanNetwork(scanResult))
            }
            rcAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.apply {
            inflate(R.menu.main_menu, menu)
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flagStartScanNetwork = requestPermissionNeeded()

        val toolbar = findViewById<Toolbar>(R.id.default_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        collapsingToolbar.title = ""

        findViewById<AppBarLayout>(R.id.app_bar_layout).addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener {
                    _ , verticalOffSet ->
                Log.i("OPTIONS_MENU", "Vertical Offset $verticalOffSet")
                // verticalOffset == 0 appbarlayout esta expandido completamente
                appBarExpanded = abs(verticalOffSet) > 200
                invalidateOptionsMenu()
            }
        )

        val rcListWifiInfo = findViewById<RecyclerView>(R.id.rc_list_of_network_wifi)
        rcAdapter = RcAdapterNearbyNetwork(this, listWifi)
        rcListWifiInfo.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = rcAdapter
        }
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
        ObservableWifiReceiver.INSTANCE.addObserver(this)
    }

    private fun startReceiverScan() {
        if(flagStartScanNetwork && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && ! isRegisterWifiReceiver) {
            wifiBroadcastReceiver = WifiBroadcastReceiver()
            val filter = IntentFilter()
            filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
            filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
            filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
            applicationContext.registerReceiver(wifiBroadcastReceiver, filter)
            periodicScan()
            isRegisterWifiReceiver = true
        }
    }


    private fun periodicScan() {
        handler = Handler()
        val context = this
        runnable = object : Runnable {
            override fun run() {
                Log.i("START_SCAN"
                    , if (WifiManagerHelper.startScan(context)) "OK" else "NOT OK")
                handler.postDelayed(this, 1000 * 60 * 3)
            }
        }
        handler.post(runnable)
    }

    private fun stopReceiverScan() {
        if(flagStartScanNetwork && isRegisterWifiReceiver) {
            this.unregisterReceiver(wifiBroadcastReceiver)
            isRegisterWifiReceiver = false
        }
    }

    override fun onPause() {
        super.onPause()
        stopReceiverScan()
    }

    override fun onDestroy() {
        super.onDestroy()
        ObservableWifiReceiver.INSTANCE.deleteObserver(this)
        handler.removeCallbacks(runnable)
    }
}
