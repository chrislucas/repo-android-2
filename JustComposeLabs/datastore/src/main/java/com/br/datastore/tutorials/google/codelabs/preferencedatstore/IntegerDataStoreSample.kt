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



const val COUNTER_INTEGER_DATA_STORE_NAME = "counter_integer_data_store"

val Context.counterIntegerDataStore: DataStore<Preferences> by preferencesDataStore(name = COUNTER_INTEGER_DATA_STORE_NAME)


fun Context.counterDataStore(key: String): Flow<Int> {
    /*
        https://developer.android.com/topic/libraries/architecture/datastore#preferences-read
     */
    return counterIntegerDataStore.data.map { data ->
        data[intPreferencesKey(key)] ?: 0
    }
}

suspend fun Context.incrementCounterIntegerDataStore(key: String) {
    counterIntegerDataStore.edit { data ->
        val k = intPreferencesKey(key)
        val currentValue = data[k] ?: 0
        data[k] = currentValue + 1

    }
}
