package com.br.funwithworkmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/*
    https://developer.android.com/develop/background-work/background-tasks/persistent/getting-started
 */
class Worker(
    context: Context,
    workerParams: WorkerParameters,
    val function: () -> Result
) : Worker(context, workerParams) {
    override fun doWork(): Result = function.invoke()
}