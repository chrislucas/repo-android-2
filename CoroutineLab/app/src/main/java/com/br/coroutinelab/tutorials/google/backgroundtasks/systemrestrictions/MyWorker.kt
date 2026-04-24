package com.br.coroutinelab.tutorials.google.backgroundtasks.systemrestrictions

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

/*
    https://developer.android.com/develop/background-work/background-tasks
 */


/*
    Determine which content authorities triggered work
    https://developer.android.com/develop/background-work/background-tasks/bg-work-restrictions#determine-which
 */


class FakeCoroutineWorker(
    context: Context,
    parameters: WorkerParameters
): CoroutineWorker(context, parameters) {
    override suspend fun doWork(): Result {
        // Simulate some work
        return try {
            // Simulate work by delaying for a few seconds
            delay(2000)
            Result.success()
        } catch (_: Exception) {
            Result.failure()
        }
    }
}
