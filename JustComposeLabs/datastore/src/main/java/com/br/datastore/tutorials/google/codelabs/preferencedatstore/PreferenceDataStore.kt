package com.br.datastore.tutorials.google.codelabs.preferencedatstore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*
    Data Store
    https://developer.android.com/topic/libraries/architecture/datastore

    https://developer.android.com/codelabs/android-preferences-datastore#1
 */



val Context.counterDataStore: DataStore<Preferences> by preferencesDataStore(name = "counter")


fun Context.counterDataStore(key: String): Flow<Int> {
    /*
        https://developer.android.com/topic/libraries/architecture/datastore#preferences-read
     */
    return counterDataStore.data.map { data ->
        data[intPreferencesKey(key)] ?: 0
    }
}

suspend fun Context.incrementCounter(key: String) {
    counterDataStore.edit { data ->
        val k = intPreferencesKey(key)
        val currentValue = data[k] ?: 0
        data[k] = currentValue + 1

    }
}