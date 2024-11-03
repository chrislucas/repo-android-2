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

    ViewElement(
        Feature(
            context.getString(R.string.txt_title_rxjava2_fun_with_sp_trans_api_bus_lines),
            "dpl://rxjava2_fun_with_sp_trans_api_bus_lines"
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_title_all_tutorials_callback_flow),
            "dpl://all_tutorials_callback_flows"
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_title_callback_flow_with_web_socket),
            "dpl://callback_flow_with_web_socket"
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_title_callback_flow_with_firebase_database_realtime),
            "dpl://callback_flow_with_firebase_database_realtime"
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_dynamic_span_drawable),
            "dpl://dynamic_span_drawable"
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_drawing_cube_on_canvas),
            "dpl://drawing_cube_canvas"
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_drawing_line_shape_canvas_book),
            "dpl://drawing_line_shape_canvas_book"
        ),
        BinderIconTextViewHolder()
    )
)





