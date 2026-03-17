package com.br.funwithdatabinding.view.features.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest

/*
    TODO ler esses links e fazer anotacoes
    https://developer.android.com/training/monitoring-device-state/connectivity-status-type

    Perform network operations overview
    https://developer.android.com/develop/connectivity/network-ops

    https://developer.android.com/reference/android/net/ConnectivityManager

    https://developer.android.com/training/monitoring-device-state/connectivity-status-type

    https://medium.com/swlh/how-to-check-internet-connection-on-android-q-ea7c5a103e3
 */


val networkRequest = NetworkRequest.Builder()
    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
    .build()


val Context.connectivityManager
    get() = getSystemService(ConnectivityManager::class.java)


val Context.networkCapabilities
    get() = with(connectivityManager) {
        getNetworkCapabilities(activeNetwork)
    }