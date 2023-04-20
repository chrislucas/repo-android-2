package com.example.feature.intnavigation.models

import android.content.Context
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.ViewHolderItem
import com.example.androidallcodelabs.R
import com.example.feature.intnavigation.view.rc.action.BindJustTextDeeplinkLayout
import com.example.feature.intnavigation.view.rc.action.BindImageAndTextDeeplinkLayout
import com.example.feature.intnavigation.view.rc.viewholder.JustTextDeeplinkViewTypeViewHolder

fun providerDeepLinks(context: Context) : List<ViewHolderItem<ViewHolder, Deeplink>> = listOf(

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
   )
)

val values = listOf<ViewHolderItem<JustTextDeeplinkViewTypeViewHolder, Deeplink>>(

)