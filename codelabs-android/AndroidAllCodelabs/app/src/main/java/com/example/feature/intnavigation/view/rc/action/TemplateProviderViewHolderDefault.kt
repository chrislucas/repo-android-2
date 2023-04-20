package com.example.feature.intnavigation.view.rc.action

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.TemplateProviderViewHolder
import com.example.feature.intnavigation.view.rc.viewholder.ImageAndTextDeeplinkViewTypeViewHolder
import com.example.feature.intnavigation.view.rc.viewholder.JustTextDeeplinkViewTypeViewHolder

class TemplateProviderViewHolderDefault(
    customEmptyStateViewHolder: ViewHolder? = null
) : TemplateProviderViewHolder(customEmptyStateViewHolder) {

    override fun find(viewRoot: ViewGroup, viewType: Int): ViewHolder? {
        val match = arrayOf<MatchViewTypeViewHolder>(
            ImageAndTextDeeplinkViewTypeViewHolder(
                viewRoot
            ),
            JustTextDeeplinkViewTypeViewHolder(
                viewRoot
            )
        ).find { match ->
            match.match(viewType)
        }
        return match as ViewHolder?
    }
}