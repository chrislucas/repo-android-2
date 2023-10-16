package com.example

import android.app.Application
import com.example.androidallcodelabs.BuildConfig
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader

class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initFlipperPlugin()
    }

    private fun initFlipperPlugin() {
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            with(AndroidFlipperClient.getInstance(this)) {
                addPlugin(
                    InspectorFlipperPlugin(this@CustomApplication, DescriptorMapping.withDefaults())
                )
                start()
            }
        }
    }
}