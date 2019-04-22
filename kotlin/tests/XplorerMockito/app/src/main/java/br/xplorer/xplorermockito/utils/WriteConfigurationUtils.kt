package br.xplorer.xplorermockito.utils

import android.content.Context
import android.util.Log
import java.io.IOException


// fonte: https://www.vogella.com/tutorials/AndroidTesting/article.html#exercise_mockingcontext_class

class WriteConfigurationUtils {
    companion object {
        fun writeConfiguration(ctx: Context, filename: String, configMessages: Array<String>) {
            try {
                val openFileOutput = ctx.openFileOutput(filename, Context.MODE_PRIVATE)
                configMessages.forEach {
                    openFileOutput.write(it.toByteArray())
                }
            } catch (e: IOException) {
                Log.e("", e.message)
            }
        }
    }
}