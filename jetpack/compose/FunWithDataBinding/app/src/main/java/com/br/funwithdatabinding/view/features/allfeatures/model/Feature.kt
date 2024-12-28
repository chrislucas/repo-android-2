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
            context.getString(R.string.txt_grouping_category_features),
            "dpl://grouping_category_features"
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_drawing_rounded_corner_text_background),
            "dpl://drawing_rounded_corner_text_background"
        ),
        BinderIconTextViewHolder()
    ),

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
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_window_insets_listeners_to_layout),
            "dpl://window_insets_listeners_to_layout"
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_compose_studying_about_scaffold),
            "dpl://compose_studying_about_scaffold"
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_compose_studying_about_app_bars),
            "dpl://compose_studying_about_app_bars"
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_get_result_from_activity),
            "dpl://get_result_from_activity"
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_simple_webkit),
            "dpl://simple_webkit"
        ),
        BinderIconTextViewHolder()
    ),
    ViewElement(
        Feature(
            context.getString(R.string.txt_open_camera_from_webview),
            "dpl://open_camera_from_web_view"
        ),
        BinderIconTextViewHolder()
    ),
    ViewElement(
        Feature(
            context.getString(R.string.txt_getting_starting_camerax_activity),
            "dpl://getting_starting_camerax_activity"
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_coordinate_transformation),
            "dpl://coordinate_transformation"
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_canvas_drawing_primitives),
            "dpl://canvas_drawing_primitives"
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_canvas_introduction_to_path),
            "dpl://canvas_introduction_to_path"
        ),
        BinderIconTextViewHolder()
    ),


    ViewElement(
        Feature(
            context.getString(R.string.txt_canvas_path_drawing_cross_line),
            "dpl://canvas_path_drawing_cross_line"
        ),
        BinderIconTextViewHolder()
    )
)





