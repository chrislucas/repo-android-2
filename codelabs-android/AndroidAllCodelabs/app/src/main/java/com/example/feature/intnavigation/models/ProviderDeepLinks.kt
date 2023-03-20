package com.example.feature.intnavigation.models

import androidx.fragment.app.FragmentActivity
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.ViewHolderData
import com.example.feature.intnavigation.view.model.DeepLinkDispatcher
import com.example.feature.intnavigation.view.model.JustTextViewType
import com.example.feature.intnavigation.view.model.TextAndImageViewType
import com.example.feature.intnavigation.view.rc.action.BindDeepLinkDispatcherViewHolder

fun providerDeepLinks(activity: FragmentActivity) : List<ViewHolderData<DeepLinkDispatcher>> = listOf(

   ViewHolderData(
      DeepLinkDispatcher(
         activity,
         Deeplink("Test Flow and Jetpack Compose", "dpl://main_activity_about_kotlin_flow")
      ),
      BindDeepLinkDispatcherViewHolder(),
      JustTextViewType()
   ),

   ViewHolderData(
      DeepLinkDispatcher(
         activity,
         Deeplink("Test Block Store Android API", "dpl://main_activity_block_store_api")
      ),
      BindDeepLinkDispatcherViewHolder(),
      TextAndImageViewType()
   ),

   ViewHolderData(
      DeepLinkDispatcher(
         activity,
         Deeplink("Test Android Property Animation", "dpl://main_property_animation")
      ),
      BindDeepLinkDispatcherViewHolder(),
      TextAndImageViewType()
   )
)