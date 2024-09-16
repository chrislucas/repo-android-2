package com.br.funwithdatabinding.view.features.allfeatures.model

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.funwithdatabinding.R
import com.br.funwithdatabinding.view.features.allfeatures.view.bindviewholder.BinderIconTextViewHolder
import com.br.funwithdatabinding.view.features.allfeatures.view.bindviewholder.BinderSimpleLinkViewHolder
import com.br.funwithdatabinding.view.features.funwithdatabinding.FunWithDataBindingActivity
import com.br.navfeatures.utils.startActivityByDeeplink
import com.br.recyclerviewcomponent.model.ViewElement

class Feature(
    val description: String,
    private val uri: String = "",
    private val intent: Intent? = null
) {
    fun fireIntention(context: Context) {
        if (uri.isNotEmpty()) {
            context.startActivityByDeeplink(uri)
        } else {
            intent?.let {
                context.startActivity(it)
            }
        }
    }
}


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





