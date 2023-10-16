package com.experience.tutorial.todoapplist.withviewslivedata.app

import android.app.Application
import com.experience.tutorial.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * https://medium.com/upday-devs/android-architecture-patterns-part-2-model-view-presenter-8a6faaae14a5
 * https://github.com/android/architecture-samples
 * https://github.com/android/architecture-samples/blob/views/app/src/main/java/com/example/android/architecture/blueprints/todoapp/TodoApplication.kt
 */

class TodoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

}