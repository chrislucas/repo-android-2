package br.xplorer.driwm.adapters.rc

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.xplorer.driwm.R
import br.xplorer.driwm.helpers.WifiManagerHelper

class RcAdapterNearbyNetwork(val listener: OnItemLongClickListener<WifiManagerHelper.InfoScanNetwork>
                             , private val list: List<WifiManagerHelper.InfoScanNetwork>) :

    RecyclerView.Adapter<RcViewHolderNearbyNetwork>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcViewHolderNearbyNetwork {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_rc_nearby_networks
            , parent, false)
        context = parent.context
        return RcViewHolderNearbyNetwork(view)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: RcViewHolderNearbyNetwork, position: Int) {
        val info = list[position]
        holder.tvWifiSsid.text = info.scanResultWifi.SSID
        holder.tvWifiBssid.text = info.scanResultWifi.BSSID
        holder.tvFrequency.text = String.format(context.getString(R.string.text_frequency), info.scanResultWifi.frequency)
        holder.bindInteraction(listener, info)
    }
}