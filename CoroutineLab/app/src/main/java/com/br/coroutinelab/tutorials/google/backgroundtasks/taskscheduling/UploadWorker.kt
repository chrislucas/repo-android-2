package com.br.coroutinelab.tutorials.google.backgroundtasks.taskscheduling

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class FakeUploadWorker(appContext: Context, workerParameters: WorkerParameters) :
    Worker(appContext, workerParameters) {
    override fun doWork(): Result {
        return try {
            // Simulate upload work
            Thread.sleep(2000)
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}