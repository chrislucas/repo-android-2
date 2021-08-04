package com.br.intercept_lifecycle_app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.util.*


interface InterceptActivityLifecycleCallback : Application.ActivityLifecycleCallbacks,
    Comparable<InterceptActivityLifecycleCallback>

class PriorityTasks(val queue: PriorityQueue<InterceptActivityLifecycleCallback>)


class CentralizedActivityLifecycleCallback(val tasks: PriorityTasks) :
    Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        tasks.queue.forEach { task ->
            task.onActivityCreated(activity, savedInstanceState)
        }
    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }
}