package com.br.navfeatures.model

import android.content.Context
import android.content.Intent
import com.br.navfeatures.utils.startActivityByDeeplink

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




