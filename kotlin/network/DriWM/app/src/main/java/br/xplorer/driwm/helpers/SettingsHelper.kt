package br.xplorer.driwm.helpers

import android.content.Context
import android.content.Intent

object SettingsHelper {

    @JvmStatic
    fun openAccessibilitySettings(context: Context) = context.startActivity(Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS))

    @JvmStatic
    fun openDataUsageSettings(context: Context) = context.startActivity(Intent(android.provider.Settings.ACTION_DATA_USAGE_SETTINGS))

}