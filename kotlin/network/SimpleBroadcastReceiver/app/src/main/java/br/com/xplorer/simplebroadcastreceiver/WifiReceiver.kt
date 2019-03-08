package br.com.xplorer.simplebroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log

import androidx.core.content.ContextCompat.getSystemService



class WifiReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val connectivityManager = getSystemService(context, android.net.ConnectivityManager::class.java)

            for (network in connectivityManager?.allNetworks!!) {
                val networkInfo = connectivityManager.getNetworkInfo(network)

                var message = "Is Connected ${networkInfo.isConnected}\n"
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val activeNetwork = connectivityManager.activeNetwork
                    val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
                    message += "Link Downstream: ${networkCapabilities.linkDownstreamBandwidthKbps}\n"
                    message += "Link Upstream: ${networkCapabilities.linkUpstreamBandwidthKbps}\n"
                    message +="Has transport CELLULAR ${networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)}\n"
                    message +="Has transport WIFI ${networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)}\n"
                    message +="Has transport BLUETOOTH ${networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH)}\n"
                    //val socket = activeNetwork.socketFactory?.createSocket()
                }

                else {
                    message += "TYPE_MOBILE ${connectivityManager.activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE}\n"
                    message += "TYPE_WIFI ${connectivityManager.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI}\n"
                }

                Log.e("WIFI_RECEIVER", message)
                //Toast.makeText(context, message, Toast.LENGTH_LONG).show()
/*
                if (networkInfo?.type == ConnectivityManager.TYPE_WIFI) {
                    isWifiConn = isWifiConn or networkInfo.isConnected
                }
                if (networkInfo?.type == ConnectivityManager.TYPE_MOBILE) {
                    isMobileConn = isMobileConn or networkInfo.isConnected
                }
                */
            }
        }


        Toast.makeText(context, intent.action, Toast.LENGTH_LONG).show()
    }
}
