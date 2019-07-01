package br.xplorer.driwm.adapters.rc

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
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

    fun setVisibileWifiConnected() {
        itemView.findViewById<ImageView>(R.id.is_connected).visibility = View.VISIBLE
    }

    fun setVisible(context: Context, isPrivate: Boolean) {
        val drawable = if(isPrivate) ContextCompat.getDrawable(context, R.drawable.private_24) else
            ContextCompat.getDrawable(context, R.drawable.not_private_network_24)
        itemView.findViewById<ImageView>(R.id.is_private).setImageDrawable(drawable)
    }
}