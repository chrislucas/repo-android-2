package com.br.funwithdatabinding.view.features.allfeatures.model

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.funwithdatabinding.R
import com.br.funwithdatabinding.view.features.funwithdatabinding.FunWithDataBindingActivity
import com.br.navfeatures.model.CategoryFeature
import com.br.navfeatures.model.Feature
import com.br.navfeatures.views.bindviewholder.BinderIconTextViewHolder
import com.br.navfeatures.views.bindviewholder.BinderSimpleLinkViewHolder
import com.br.recyclerviewcomponent.model.ViewElement

fun getFeatures(context: Context): List<ViewElement<Feature, ViewHolder>> = listOf(

    ViewElement(
        Feature(
            context.getString(R.string.show_case_section_view_based_or_compose),
            "dpl://show_case_section_view_based_or_compose",
            category = CategoryFeature.VIEW_BASED
        ),
        BinderIconTextViewHolder(),
    ),


    ViewElement(
        Feature(
            context.getString(R.string.txt_bridge_js),
            "dpl://bridge_js",
            category = CategoryFeature.VIEW_BASED
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_grouping_category_features),
            "dpl://grouping_category_features",
            category = CategoryFeature.VIEW_BASED
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_drawing_rounded_corner_text_background),
            "dpl://drawing_rounded_corner_text_background",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_title_code_labs_android_databinding),
            "dpl://code_labs_android_databinding",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderSimpleLinkViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_title_rxjava2_fun_with_sp_trans_api),
            "dpl://rxjava2_fun_with_sp_trans_api",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_title_fun_with_databinding),
            intent = Intent(context, FunWithDataBindingActivity::class.java),
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),


    ViewElement(
        Feature(
            context.getString(R.string.txt_title_rxjava3_fun_with_sp_trans_api_authentication),
            "dpl://rxjava3_fun_with_sp_trans_api_authentication",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_title_rxjava2_fun_with_sp_trans_api_bus_lines),
            "dpl://rxjava2_fun_with_sp_trans_api_bus_lines",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_title_all_tutorials_callback_flow),
            "dpl://all_tutorials_callback_flows",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_title_callback_flow_with_web_socket),
            "dpl://callback_flow_with_web_socket",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_title_callback_flow_with_firebase_database_realtime),
            "dpl://callback_flow_with_firebase_database_realtime",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_dynamic_span_drawable),
            "dpl://dynamic_span_drawable",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_drawing_cube_on_canvas),
            "dpl://drawing_cube_canvas",
            category = CategoryFeature.VIEW_BASED
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_drawing_line_shape_canvas_book),
            "dpl://drawing_line_shape_canvas_book",
            category = CategoryFeature.VIEW_BASED
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_window_insets_listeners_to_layout),
            "dpl://window_insets_listeners_to_layout",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_compose_studying_about_scaffold),
            "dpl://compose_studying_about_scaffold",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_compose_studying_about_app_bars),
            "dpl://compose_studying_about_app_bars",
            category = CategoryFeature.VIEW_BASED
        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_get_result_from_activity),
            "dpl://get_result_from_activity",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_simple_webkit),
            "dpl://simple_webkit",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),
    ViewElement(
        Feature(
            context.getString(R.string.txt_open_camera_from_webview),
            "dpl://open_camera_from_web_view",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),
    ViewElement(
        Feature(
            context.getString(R.string.txt_getting_starting_camerax_activity),
            "dpl://getting_starting_camerax_activity",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_coordinate_transformation),
            "dpl://coordinate_transformation",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_canvas_drawing_primitives),
            "dpl://canvas_drawing_primitives",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_canvas_introduction_to_path),
            "dpl://canvas_introduction_to_path",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),


    ViewElement(
        Feature(
            context.getString(R.string.txt_canvas_path_drawing_cross_line),
            "dpl://canvas_path_drawing_cross_line",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),


    ViewElement(
        Feature(
            context.getString(R.string.txt_canvas_path_drawing_drawing_polygons),
            "dpl://canvas_playing_with_path",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_introduction_open_gl_android),
            "dpl://introduction_open_gl_android",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_canvas_drawing_dashed_line),
            "dpl://canvas_drawing_dashed_line",
            category = CategoryFeature.VIEW_BASED

        ),
        BinderIconTextViewHolder()
    ),

    ViewElement(
        Feature(
            context.getString(R.string.txt_canvas_drawing_circle),
            "dpl://canvas_drawing_circle",
            category = CategoryFeature.VIEW_BASED
        ),
        BinderIconTextViewHolder()
    )
)


val Context.viewBasedFeatures: List<ViewElement<Feature, ViewHolder>>
    get() = getFeatures(this).filter { it.content.category == CategoryFeature.VIEW_BASED }

val Context.composeBasedFeatures: List<ViewElement<Feature, ViewHolder>>
    get() = getFeatures(this).filter { it.content.category == CategoryFeature.COMPOSE }




