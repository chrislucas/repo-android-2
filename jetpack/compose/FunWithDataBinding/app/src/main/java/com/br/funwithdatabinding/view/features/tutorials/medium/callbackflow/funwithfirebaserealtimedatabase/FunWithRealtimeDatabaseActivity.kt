package com.br.funwithdatabinding.view.features.tutorials.medium.callbackflow.funwithfirebaserealtimedatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R


/**
 * TODO
 * Firebase Realtime Database
 * https://firebase.google.com/docs/database
 *
 * Callback Flow
 * https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/callback-flow.html#fromHistory
 *
 * https://www.droidcon.com/2023/05/01/callbackflow-with-firebase-converting-realtimedatabase-callbacks-into-callbackflow/

Kotlin callbackFlow: A lightweight architecture for location-aware Android apps

https://barbeau.medium.com/kotlin-callbackflow-a-lightweight-architecture-for-location-aware-android-apps-f97b5e66aaa2#fromHistory

 */
class FunWithRealtimeDatabaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fun_with_realtime_database)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recycler_view_all_features)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}