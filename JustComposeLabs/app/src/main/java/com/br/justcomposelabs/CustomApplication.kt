package com.br.justcomposelabs

import android.app.Application
import android.os.StrictMode
import timber.log.Timber

class CustomApplication : Application() {

    override fun onCreate() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())

            /*
                https://developer.android.com/reference/android/os/StrictMode

                https://medium.com/@sivavishnu0705/mastering-stability-a-deep-dive-into-androids-strictmode-0073b595bb81
                https://blog.stackademic.com/strictmode-coroutines-catch-android-anrs-before-users-do-0ee72765302b
             */

            // 1. Thread Policy: Detecta rede/disco na UI Thread
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll() // Detecta leituras, escritas e rede
                    .penaltyLog() // Loga no Logcat
                    // .penaltyFlashScreen() // Pisca a tela para avisar
                    .build()
            )

            // 2. VM Policy: Detecta vazamentos de memória (Activities, Closables)
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    // .detectLeakedSqlLiteObjects()
                    // .detectLeakedClosableObjects() // Helps catch unclosed streams, a common I/O oversight
                    // .detectActivityLeaks()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
        }

        super.onCreate()
    }
}
