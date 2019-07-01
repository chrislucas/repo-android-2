package br.xplorer.driwm.adapters.rc

import android.content.Context
import android.net.wifi.ScanResult
import android.net.wifi.WifiInfo
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.xplorer.driwm.R
import br.xplorer.driwm.helpers.WifiManagerHelper

import br.xplorer.driwm.helpers.WifiManagerHelper.logi

class RcAdapterNearbyNetwork(
    private val listener: OnItemLongClickListener<WifiManagerHelper.InfoScanNetwork>
    , private val list: List<WifiManagerHelper.InfoScanNetwork>) :

    RecyclerView.Adapter<RcViewHolderNearbyNetwork>() {

    private lateinit var context: Context
    lateinit var wifiInfo: WifiInfo


    fun isSameNetwork (scanResultWifi: ScanResult) : Boolean {
        return scanResultWifi.run { this.SSID == wifiInfo.ssid.replace("\"", "") }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcViewHolderNearbyNetwork {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_rc_nearby_networks
            , parent, false)
        context     = parent.context
        wifiInfo    = WifiManagerHelper.getWifiInfo(parent.context)
        wifiInfo.logi("WIFI_INFO", context)
        return RcViewHolderNearbyNetwork(view)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: RcViewHolderNearbyNetwork, position: Int) {
        val info = list[position].scanResultWifi
        if(isSameNetwork(info))
            holder.setVisibileWifiConnected()

        holder.tvWifiSsid.text  = info.SSID
        holder.tvWifiBssid.text = info.BSSID
        holder.tvFrequency.text = String.format(context.getString(R.string.text_frequency), info.frequency)
        holder.bindInteraction(listener, list[position])
    }
}