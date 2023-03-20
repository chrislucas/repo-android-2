package com.example.openactivity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager

fun Context.redirectByDeeplink(deeplink: String, flags: Int, fn: (() -> Unit)? = null) {
    with(this) {
        val resolveInfo = packageManager.resolveActivity(
            Intent.parseUri(deeplink, flags),
            PackageManager.GET_RESOLVED_FILTER or PackageManager.MATCH_DEFAULT_ONLY
        )

        resolveInfo?.let {
            this.redirect(it.activityInfo.createIntent())
        } ?: fn?.invoke()
    }
}

private fun Context.redirect(intent: Intent) = startActivity(intent)

private fun ActivityInfo.createIntent(): Intent {
    return with(this@createIntent) {
        val c = ComponentName(this.packageName, this.name)
        with(Intent()) {
            this.apply { this.component = c }
        }
    }
}
