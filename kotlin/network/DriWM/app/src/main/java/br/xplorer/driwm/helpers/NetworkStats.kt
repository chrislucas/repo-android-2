package br.xplorer.driwm.helpers

import android.app.usage.NetworkStatsManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

object NetworkStats {

    @RequiresApi(Build.VERSION_CODES.M)
    fun get(context: Context) : NetworkStatsManager =  context.applicationContext
        .getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager
}