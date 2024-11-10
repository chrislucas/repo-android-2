package com.br.funwithdatabinding.view.features.allfeatures.model

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.br.funwithdatabinding.R
import com.br.navfeatures.model.Feature
import com.br.navfeatures.views.bindviewholder.BinderIconTextViewHolder
import com.br.recyclerviewcomponent.model.ViewElement

fun getCategoryFeature(context: Context) : List<ViewElement<Feature, RecyclerView.ViewHolder>> =
    listOf(
        ViewElement(
            Feature(
                context.getString(R.string.txt_spannable_category),
                "dpl://e"
            ),
            BinderIconTextViewHolder()
        ),

        ViewElement(
            Feature(
                context.getString(R.string.txt_security_category),
                "dpl://e"
            ),
            BinderIconTextViewHolder()
        )
    )



class CategoryTutorial(val tutorials: List<Tutorial>)


class Tutorial(val deeplink: String)