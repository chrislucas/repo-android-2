package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.viewtype.ViewHolderType

interface ViewHolderBuilder {
    fun build(viewGroup: ViewGroup): RecyclerView.ViewHolder
    fun getViewHolderType(): ViewHolderType
}
