package com.br.funwithdatabinding.view.features.tutorials.google.notification

import android.content.Context
import androidx.core.app.NotificationCompat

/*
    TODO
    https://developer.android.com/develop/ui/views/notifications/build-notification
 */


fun Context.notificationBuilder(channelId: String, title: String) =
    NotificationCompat.Builder(this, channelId)
        .setContentTitle(title)