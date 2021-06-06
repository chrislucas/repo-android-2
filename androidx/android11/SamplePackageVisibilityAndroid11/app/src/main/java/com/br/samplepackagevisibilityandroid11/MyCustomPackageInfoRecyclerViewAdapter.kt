package com.br.samplepackagevisibilityandroid11

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.samplepackagevisibilityandroid11.models.WrapperPackageInfo


class MyCustomPackageInfoRecyclerViewAdapter(
    private val values: List<WrapperPackageInfo>
) : RecyclerView.Adapter<MyCustomPackageInfoRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_custom_package_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        item.let {
            it.packageInfo.let {
                packageInfo ->
                packageInfo.applicationInfo.let {
                    appInfo ->

                }
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}