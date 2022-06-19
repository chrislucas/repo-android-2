package com.br.experience.funmobdatascience.views.list.viewholder.builder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntDef
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.br.experience.funmobdatascience.R

object BuilderViewHolder {

    @IntDef(
        VIEW_HOLDER_DEFAULT,
        VIEW_HOLDER_EMPTY_STATE,
        ALTERNATIVE_VIEW_HOLDER
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class ViewType

    const val VIEW_HOLDER_EMPTY_STATE = 0
    const val VIEW_HOLDER_DEFAULT = 1
    const val ALTERNATIVE_VIEW_HOLDER = -1

    @JvmStatic
    fun build(
        @ViewType viewType: Int,
        viewGroup: ViewGroup,
        alternativeBuilder: (() -> RecyclerView.ViewHolder)? = null
    ): RecyclerView.ViewHolder {

         fun tryAlternativeSolution() : RecyclerView.ViewHolder {
            return alternativeBuilder?.invoke()
                ?: throw IllegalArgumentException(
                    "Nenhum ViewType foi identificado e a funcao" +
                            " de construir ViewHolder nao foi definida"
                )
        }

        return when (viewType) {
            VIEW_HOLDER_EMPTY_STATE -> {
                EmptyStateViewHolder(getView(viewGroup, R.layout.layout_empty_state_view_holder))
            }
            VIEW_HOLDER_DEFAULT -> {
                DefaultViewHolder(getView(viewGroup, R.layout.layout_default_view_holder))
            }
            ALTERNATIVE_VIEW_HOLDER -> {
                tryAlternativeSolution()
            }
            else -> {
                tryAlternativeSolution()
            }
        }
    }

    private fun getView(viewGroup: ViewGroup, @LayoutRes layout: Int): View =
        LayoutInflater.from(viewGroup.context).inflate(layout, viewGroup, false)
}