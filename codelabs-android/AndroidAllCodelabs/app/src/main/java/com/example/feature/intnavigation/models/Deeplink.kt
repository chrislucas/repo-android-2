package com.example.feature.intnavigation.models

import android.content.Intent

class Deeplink(
    val description: String,
    val uri: String = "",
    val intent: Intent? = null
)