package com.br.experience.funmobdatascience.views.list.viewholder.builder

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.br.experience.funmobdatascience.databinding.LayoutDefaultViewHolderBinding

class DefaultViewHolder(viewRoot: View) : RecyclerView.ViewHolder(viewRoot) {
    val binding: LayoutDefaultViewHolderBinding by lazy { LayoutDefaultViewHolderBinding.inflate(LayoutInflater.from(viewRoot.context)) }
}