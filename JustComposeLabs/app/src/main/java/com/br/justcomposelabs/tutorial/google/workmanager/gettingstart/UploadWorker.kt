package com.br.justcomposelabs.tutorial.google.workmanager.gettingstart

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters

/*
    https://developer.android.com/develop/background-work/background-tasks/persistent/getting-started
 */

interface Uploader {
    fun upload(): ListenableWorker.Result
}

class UploadWorker(ctx: Context, private val uploader: Uploader, workerParams: WorkerParameters) :
    Worker(ctx, workerParams) {
    override fun doWork(): Result {
        return uploader.upload()
    }
}