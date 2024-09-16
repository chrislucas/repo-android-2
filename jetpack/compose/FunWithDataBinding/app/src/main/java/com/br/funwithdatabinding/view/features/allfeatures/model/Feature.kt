package com.br.funwithdatabinding.view.features.allfeatures.model

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.funwithdatabinding.R
import com.br.funwithdatabinding.view.features.funwithdatabinding.FunWithDataBindingActivity
import com.br.navfeatures.model.Feature
import com.br.navfeatures.views.bindviewholder.BinderIconTextViewHolder
import com.br.navfeatures.views.bindviewholder.BinderSimpleLinkViewHolder
import com.br.recyclerviewcomponent.model.ViewElement

fun getFeatures(context: Context): List<ViewElement<Feature, ViewHolder>> = listOf(
    ViewElement(
        Feature(
            context.getString(R.string.txt_title_code_labs_android_databinding),
            "dpl://code_labs_android_databinding"
        ),
        BinderSimpleLinkViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_title_rxjava2_fun_with_sp_trans_api),
            "dpl://rxjava2_fun_with_sp_trans_api"
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_title_fun_with_databinding),
            intent = Intent(context, FunWithDataBindingActivity::class.java)
        ),
        BinderIconTextViewHolder()
    ),


    ViewElement(
        Feature(
            context.getString(R.string.txt_title_rxjava3_fun_with_sp_trans_api_authentication),
            "dpl://rxjava3_fun_with_sp_trans_api_authentication"
        ),
        BinderIconTextViewHolder()
    ),
)





