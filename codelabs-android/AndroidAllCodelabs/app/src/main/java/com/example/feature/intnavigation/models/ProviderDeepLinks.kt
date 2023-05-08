package com.example.feature.intnavigation.models

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.ViewHolderItem
import com.example.androidallcodelabs.R
import com.example.feature.android.api.desugaring.ExplorerDesugaringApiActivity
import com.example.feature.android.api.v33.shaders.AboutProgrammbleShadersActivity
import com.example.feature.android.api.v33.shaders.AndroidGraphicsShadingLanguageActivity
import com.example.feature.android.api.v33.shaders.OpenGLActivity
import com.example.feature.intnavigation.view.rc.action.BindJustTextDeeplinkLayout
import com.example.feature.intnavigation.view.rc.action.BindImageAndTextDeeplinkLayout

fun providerDeepLinks(context: Context): List<ViewHolderItem<ViewHolder, Deeplink>> = listOf(

    ViewHolderItem(
        Deeplink(context.getString(R.string.txt_title_deeplink_activity_about_kotlin_flow), "dpl://main_activity_about_kotlin_flow"),
        BindJustTextDeeplinkLayout()
    ),

    ViewHolderItem(
        Deeplink(context.getString(R.string.txt_title_deeplink_activity_block_store_api), "dpl://main_activity_block_store_api"),
        BindImageAndTextDeeplinkLayout()
    ),

    ViewHolderItem(
        Deeplink(context.getString(R.string.txt_title_deeplink_activity_property_animation), "dpl://main_property_animation"),
        BindImageAndTextDeeplinkLayout()
    ),

    ViewHolderItem(
        Deeplink(
            context.getString(R.string.txt_title_activity_desugaring_api),
            intent = Intent(context, ExplorerDesugaringApiActivity::class.java)
        ),
        BindJustTextDeeplinkLayout()
    ),

    ViewHolderItem(
        Deeplink(
            context.getString(R.string.txt_title_activity_programming_shaders_android_13),
            intent = Intent(context, AboutProgrammbleShadersActivity::class.java)
        ),
        BindJustTextDeeplinkLayout()
    ),

    ViewHolderItem(
        Deeplink(
            context.getString(R.string.txt_title_activity_agsl),
            intent = Intent(context, AndroidGraphicsShadingLanguageActivity::class.java)
        ),
        BindJustTextDeeplinkLayout()
    ),

    ViewHolderItem(
        Deeplink(
            context.getString(R.string.txt_title_activity_open_gl_test_1),
            intent = Intent(context, OpenGLActivity::class.java)
        ),
        BindJustTextDeeplinkLayout()
    )
)