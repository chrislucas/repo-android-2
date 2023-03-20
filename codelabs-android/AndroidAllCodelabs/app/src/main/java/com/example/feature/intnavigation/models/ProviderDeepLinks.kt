package com.example.feature.intnavigation.models

import android.content.Context
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.ViewHolderData
import com.example.androidallcodelabs.R
import com.example.feature.intnavigation.view.model.JustTextDeepLinkViewType
import com.example.feature.intnavigation.view.model.ImageAndTextDeeplinkViewType
import com.example.feature.intnavigation.view.rc.action.BindDeepLinkJustTextViewHolderLayout

fun providerDeepLinks(context: Context) : List<ViewHolderData<Deeplink>> = listOf(

   ViewHolderData(
      Deeplink(context.getString(R.string.txt_title_deeplink_activity_about_kotlin_flow), "dpl://main_activity_about_kotlin_flow"),
      BindDeepLinkJustTextViewHolderLayout(),
      JustTextDeepLinkViewType()
   ),

   ViewHolderData(
      Deeplink(context.getString(R.string.txt_title_deeplink_activity_block_store_api), "dpl://main_activity_block_store_api"),
      BindDeepLinkJustTextViewHolderLayout(),
      ImageAndTextDeeplinkViewType()
   ),

   ViewHolderData(
      Deeplink(context.getString(R.string.txt_title_deeplink_activity_property_animation), "dpl://main_property_animation"),
      BindDeepLinkJustTextViewHolderLayout(),
      ImageAndTextDeeplinkViewType()
   )
)