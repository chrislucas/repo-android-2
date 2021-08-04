package com.br.samplepackagevisibilityandroid11

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            getLogInstalledModules()
        }
        getLogApplicationInstalled()
        getLogInstalledPackages()

    }


    @RequiresApi(Build.VERSION_CODES.Q)
    private fun getLogInstalledModules() {
        val pair = packageManager.getLogInstalledModules()
        val tag = "LOG_INSTALLED_MODULES"
        Log.i(tag, "OR Operation on all flags:\nBuild:{${Build.VERSION.SDK_INT}}\n$pair")
    }

    private fun getLogInstalledPackages() {
        val tag = "LOG_INSTALLED_PACK"
        val pair = packageManager.getLogInstalledPackages()
        Log.i(
            tag, String.format(
                "MATCH_ALL(%x),  FLAGS_REDUCE_OR_OPERATION(%x)",
                PackageManager.MATCH_ALL, resultOrOperationFlags
            )
        )
        Log.i(tag, "OR Operation on all flags:\nBuild:{${Build.VERSION.SDK_INT}}\n$pair")
    }


    private fun getLogApplicationInstalled() {
        val map = packageManager.getLogApplicationInstalled()

        val tag = "LOG_APP_INSTALLED"

        Log.i(tag, "$map")

        val pair = Pair(
            resultOrOperationFlags,
            packageManager.getInstalledApplications(resultOrOperationFlags)
        )

        Log.i(
            tag,
            String.format(
                "MATCH_ALL(%x),  FLAGS_REDUCE_OR_OPERATION(%x)",
                PackageManager.MATCH_ALL, resultOrOperationFlags
            )
        )
        Log.i(
            tag,
            "OR Operation on all flags:\nBuild:{${Build.VERSION.SDK_INT}}\n$pair"
        )
    }
}