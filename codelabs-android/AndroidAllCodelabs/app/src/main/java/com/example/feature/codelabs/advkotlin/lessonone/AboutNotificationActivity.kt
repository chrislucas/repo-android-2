package com.example.feature.codelabs.advkotlin.lessonone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidallcodelabs.R

/**
 * https://developer.android.com/codelabs/advanced-android-kotlin-training-notifications#0

    Esse codelab faz parte de uma série que orienta você no uso de notificações push e notificações do app.

 */

class AboutNotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_notification)
    }
}