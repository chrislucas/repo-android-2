package com.example.feature.intnavigation.view.model

import androidx.fragment.app.FragmentActivity
import com.example.feature.intnavigation.models.Deeplink

data class DeepLinkDispatcher(val fragmentActivity: FragmentActivity, val deepLink: Deeplink)