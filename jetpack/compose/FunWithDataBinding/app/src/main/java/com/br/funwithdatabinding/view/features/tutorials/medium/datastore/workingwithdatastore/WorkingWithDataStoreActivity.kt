package com.br.funwithdatabinding.view.features.tutorials.medium.datastore.workingwithdatastore

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.preferences.preferencesDataStore
import com.br.funwithdatabinding.R


/**
 * https://nglauber.medium.com/jetpack-datastore-c5033dc53f19
 *
 * Proto Vs DataStore
 * https://developer.android.com/topic/libraries/architecture/datastore#prefs-vs-proto
 */
class WorkingWithDataStoreActivity : AppCompatActivity() {

    private val Context.dataStore by preferencesDataStore("app_preferences")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_working_with_data_store)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}