package br.xplorer.driwm

import android.Manifest
import android.content.DialogInterface
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.xplorer.driwm.adapters.rc.OnItemLongClickListener
import br.xplorer.driwm.adapters.rc.RcAdapterNearbyNetwork
import br.xplorer.driwm.helpers.ViewHelper
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

    private lateinit var alertDialog : AlertDialog


    private lateinit var rcAdapter: RcAdapterNearbyNetwork
    private val listWifi = mutableListOf<WifiManagerHelper.InfoScanNetwork>()

    private var flagStartScanNetwork = false
    private var isRegisterWifiReceiver = false
    private var appBarExpanded = false
    private var isRunningScan = false



    override fun onLongClick(item: WifiManagerHelper.InfoScanNetwork) : Boolean {
        if(rcAdapter.isSameNetwork(item.scanResultWifi)) {
            // Perguntar ao usuario se ele quer memso apagar os dados da rede
            val builderAlertDialog = AlertDialog.Builder(this)
            val title = String.format(getString(R.string.title_confirm_disable_wifi_network)
                , item.scanResultWifi.SSID)
            builderAlertDialog.setTitle(title)
            val message = String.format(getString(R.string.message_confirm_disable_wifi_network)
                , item.scanResultWifi.SSID)
            builderAlertDialog.setMessage(message)
            val context = this
            val listener = DialogInterface.OnClickListener { dialog, which ->
                when(which) {
                    DialogInterface.BUTTON_NEGATIVE -> dialog?.dismiss()
                    DialogInterface.BUTTON_POSITIVE -> {
                        var networiId = 0
                        WifiManagerHelper.getWifiManager(context).configuredNetworks.forEach {
                            Log.i("WIFI_CONFIG", "${it.networkId}, ${it.BSSID}")
                            if(it.SSID.replace("\"", "") == item.scanResultWifi.SSID)
                                networiId = it.networkId
                        }
                        val disable = WifiManagerHelper.resetConfigWifi(context, networiId)
                        val messageAfterResetConfigWifi = if(disable) {
                            "As configuracoes da rede ${item.scanResultWifi.SSID} foram apagadas"
                        } else {
                            "Erro ao apagar as configuracoes da rede ${item.scanResultWifi.SSID}"
                        }
                        runOnUiThread {
                            Toast.makeText(context, messageAfterResetConfigWifi
                                , Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

            builderAlertDialog.setNegativeButton(R.string.no, listener)
            builderAlertDialog.setPositiveButton(R.string.yes, listener)
            builderAlertDialog.show()
        }

        return true
    }

    override fun onItemClick(item: WifiManagerHelper.InfoScanNetwork) {}

    override fun update(observable: Observable?, data: Any?) {
        dismissSafelyAlertDialog(alertDialog)
        isRunningScan = false
        if (data != null && data is Boolean && data) {
            val wm = WifiManagerHelper.getWifiManager(this)
            val results = wm.scanResults
            val it = listWifi.iterator()
            while (it.hasNext()) {
                it.next()
                it.remove()
            }
            results.forEach { scanResult -> listWifi.add(WifiManagerHelper.InfoScanNetwork(scanResult)) }
            rcAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.apply {
            inflate(R.menu.main_menu, menu)
        }
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.item_start_wifi_scane -> {
                startScan()
                true
            }
            R.id.item_wireless_setting -> {
                WifiManagerHelper.openWirelessSettings(this)
                true
            }
            R.id.item_wifi_setting -> {
                WifiManagerHelper.openWifiSettings(this)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
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
                //Log.i("OPTIONS_MENU", "Vertical Offset $verticalOffSet")
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
        alertDialog = ViewHelper.buildProgressBar(this).create()
        startReceiverScan()
        ObservableWifiReceiver.INSTANCE.addObserver(this)
    }

    private fun startReceiverScan() {
        if(flagStartScanNetwork && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && ! isRegisterWifiReceiver) {
            showSafelyAlertDialog(alertDialog)
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
        runnable = object : Runnable {
            override fun run() {
                Log.i("START_SCAN", if (startScan()) "OK" else "NOT OK")
                handler.postDelayed(this, 1000 * 60 * 15)
            }
        }
        handler.post(runnable)
    }

    private fun startScan() : Boolean  {
        return if(!isRunningScan) {
            WifiManagerHelper.startScan(this)
            isRunningScan = true
            true
        }
        else {
            runOnUiThread {
                Toast.makeText(this
                    , "Já está sendo executado uma verificação de rede"
                    , Toast.LENGTH_SHORT).show()
            }
            false
        }
    }

    private fun stopReceiverScan() {
        if(flagStartScanNetwork && isRegisterWifiReceiver) {
            //this.unregisterReceiver(wifiBroadcastReceiver)
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
        dismissSafelyAlertDialog(alertDialog)
    }
}
