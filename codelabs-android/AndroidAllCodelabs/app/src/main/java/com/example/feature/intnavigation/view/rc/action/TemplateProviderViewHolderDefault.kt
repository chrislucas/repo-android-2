package com.example.feature.intnavigation.view.rc.action

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.TemplateProviderViewHolder
import com.example.feature.intnavigation.view.rc.viewholder.ImageAndTextDeeplinkViewHolder
import com.example.feature.intnavigation.view.rc.viewholder.JustTextDeeplinkViewHolder

class TemplateProviderViewHolderDefault(customEmptyStateViewHolder: RecyclerView.ViewHolder? = null) :
    TemplateProviderViewHolder(customEmptyStateViewHolder) {

    override fun find(viewRoot: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return arrayOf<MatchViewHolder>(
            ImageAndTextDeeplinkViewHolder(viewRoot),
            JustTextDeeplinkViewHolder(viewRoot)
        ).find { match ->
            match.match(viewType)
        }?.getViewHolder()
    }
}