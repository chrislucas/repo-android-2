package com.br.justcomposelabs.tutorial.google.view.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.justcomposelabs.databinding.SampleCardForSampleRecyclerViewBinding
import timber.log.Timber

/*
    https://developer.android.com/develop/ui/views/layout/recyclerview
    Criar um adapter que permita criar uma lista circulas, ao chegar no ultimo item
    retornar para o primeiro


    Getting started with Material components for Android

    https://github.com/material-components/material-components-android/blob/master/docs/getting-started.md
    https://github.com/material-components/material-components-android/tree/master
 */


class CircularAdapter<T, V : RecyclerView.ViewHolder>(
    private val dataSet: List<T>,
    private val viewHolderFactory: (ViewGroup, viewType: Int) -> V,
    private val viewHolderBind: (V, T) -> Unit
) : RecyclerView.Adapter<V>() {

    private var virtualSizeAdapter = if (dataSet.isNotEmpty()) {
        10
    } else {
        0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        return viewHolderFactory(parent, viewType)
    }

    override fun onBindViewHolder(holder: V, position: Int) {
        if (dataSet.isNotEmpty()) {
            Timber.d("position: $position, virtualSizeAdapter: $virtualSizeAdapter")
            viewHolderBind(holder, dataSet[position % dataSet.size])
        }
    }

    override fun getItemCount(): Int = virtualSizeAdapter


    companion object {
        const val VIRTUAL_LIMIT = 10
    }
}

class CircularDefaultViewHolder(view: ViewGroup) : RecyclerView.ViewHolder(
    SampleCardForSampleRecyclerViewBinding.inflate(
        LayoutInflater.from(view.context),
        view,
        false
    ).root
) {
    private val bind = SampleCardForSampleRecyclerViewBinding.bind(itemView)

    fun setContent(content: String) {
        bind.contentCard.text = content
    }
}


class CircularScrollListener: RecyclerView.OnScrollListener() {
    /*
        https://medium.com/better-programming/what-goes-around-comes-around-1aae51da0f29
     */
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val firstItemVisible = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        if (firstItemVisible > 0 && firstItemVisible % CircularAdapter.VIRTUAL_LIMIT == 0) {
            recyclerView.layoutManager?.scrollToPosition(0)
        }
    }
}