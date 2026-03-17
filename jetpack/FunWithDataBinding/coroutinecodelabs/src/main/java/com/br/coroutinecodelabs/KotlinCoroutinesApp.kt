package com.br.coroutinecodelabs

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import com.br.coroutinecodelabs.start.OwnWorkerFactory
import com.br.coroutinecodelabs.start.RefreshMainDataWorker

/*
    https://developer.android.com/codelabs/kotlin-coroutines#1
    https://github.com/android/codelab-kotlin-coroutines/blob/master/coroutines-codelab/start/src/main/AndroidManifest.xml
 */

class KotlinCoroutinesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        setupWorkManager()
    }


    private fun setupWorkManager() {
        val workManagerConfig = Configuration.Builder()
            .setWorkerFactory(OwnWorkerFactory())
            .build()

        WorkManager.initialize(this, workManagerConfig)
    }
}