package com.br.deeplinkandintentfilter

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class CheckDeeplink : AppCompatActivity() {

    private val tvDeeplink: TextInputEditText by lazy { findViewById(R.id.text_deeplink) }
    private val btCheckDeeplink: Button by lazy { findViewById(R.id.bt_check_deeplink) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_deeplink)


        btCheckDeeplink.setOnClickListener {
            val deeplink = tvDeeplink.text.toString()

            if (deeplink.isNotEmpty()) {
                resolveActivity(deeplink)
                queryIntentActivities(deeplink)
            }
        }
    }

    private fun resolveActivity(deeplink: String) {
        val resolveInfo = packageManager.resolveActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse(deeplink)),
            PackageManager.GET_RESOLVED_FILTER or PackageManager.MATCH_DEFAULT_ONLY
        )

        resolveInfo?.run {
            this.logFilterActionIterator()
            this.logResolvePackageName()
        }
    }

    private fun queryIntentActivities(deeplink: String) {
        val resolveInfoList = packageManager.queryIntentActivities(
            Intent(Intent.ACTION_VIEW, Uri.parse(deeplink)),
            PackageManager.GET_RESOLVED_FILTER or PackageManager.MATCH_DEFAULT_ONLY
        )

        println(resolveInfoList.size)
    }
}


private fun ResolveInfo.logFilterActionIterator() {
    this.filter?.actionsIterator()?.forEach {
        println(it)
    }
}

private fun ResolveInfo.logResolvePackageName() {
    println(this.resolvePackageName)
}


private fun List<ResolveInfo>.log() {
    for (resolveInfo in this) {
        resolveInfo.logResolvePackageName()
    }
}
