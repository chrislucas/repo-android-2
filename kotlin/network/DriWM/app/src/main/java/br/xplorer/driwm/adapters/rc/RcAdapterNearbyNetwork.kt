package br.xplorer.driwm.adapters.rc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.xplorer.driwm.R
import br.xplorer.driwm.helpers.WifiManagerHelper

class RcAdapterNearbyNetwork(val listener: OnItemClickListener<WifiManagerHelper.DataNearbyNetwork>
                             , private val list: List<WifiManagerHelper.DataNearbyNetwork>) :

    RecyclerView.Adapter<RcViewHolderNearbyNetwork>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcViewHolderNearbyNetwork {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_rc_nearby_networks
            , parent, false)
        return RcViewHolderNearbyNetwork(view)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: RcViewHolderNearbyNetwork, position: Int) {}
}