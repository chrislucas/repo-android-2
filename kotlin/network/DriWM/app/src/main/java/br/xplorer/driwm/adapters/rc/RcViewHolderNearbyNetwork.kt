package br.xplorer.driwm.adapters.rc

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.xplorer.driwm.R
import br.xplorer.driwm.helpers.WifiManagerHelper


class RcViewHolderNearbyNetwork(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvWifiBssid: TextView = itemView.findViewById(R.id.tv_bssid)
    val tvWifiSsid: TextView = itemView.findViewById(R.id.tv_ssid)
    val tvFrequency: TextView = itemView.findViewById(R.id.tv_frequency)

    fun bindInteraction(listener: OnItemLongClickListener<WifiManagerHelper.InfoScanNetwork>
                        , info: WifiManagerHelper.InfoScanNetwork) {
        itemView.setOnClickListener {
            listener.onItemClick(info)
        }

        itemView.setOnLongClickListener {
            listener.onLongClick(info)
        }
    }
}