package com.br.coroutinecodelabs.start

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters

class OwnWorkerFactory(private val doWork: () -> ListenableWorker.Result = { ListenableWorker.Result.success() }) :
    WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? =
        RefreshMainDataWorker(appContext, workerParameters, doWork)
}


internal class RefreshMainDataWorker(
    appContext: Context,
    params: WorkerParameters,
    private val function: () -> Result
) :
    CoroutineWorker(appContext, params = params) {
    override suspend fun doWork(): Result = this.function()

}