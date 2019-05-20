package br.xplorer.xplorerheaderanimation.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.xplorer.xplorerheaderanimation.R
import br.xplorer.xplorerheaderanimation.adapters.listener.OnClickItemListener
import br.xplorer.xplorerheaderanimation.models.ActivityIntent

class AdapterListActivityIntent(private val data: List<ActivityIntent>, private val onClickItem: OnClickItemListener<ActivityIntent>) :
    RecyclerView.Adapter<AdapterListActivityIntent.VHListActivityIntent>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): VHListActivityIntent {
        val viewRoot = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_rc_simple_item, parent, false)
        return VHListActivityIntent(viewRoot)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(viewHolder: VHListActivityIntent, pos: Int) {
        viewHolder.textItem.text = data[pos].className
        viewHolder.bindOnClick(data[pos], onClickItem)
    }

    class VHListActivityIntent(viewRoot: View) : RecyclerView.ViewHolder(viewRoot) {

        val textItem: TextView = viewRoot.findViewById(R.id.title_activity_intent)

        fun bindOnClick(activityIntent: ActivityIntent, onClickItem: OnClickItemListener<ActivityIntent>) {
            itemView.setOnClickListener {
                onClickItem.onClick(activityIntent)
            }
        }
    }

}