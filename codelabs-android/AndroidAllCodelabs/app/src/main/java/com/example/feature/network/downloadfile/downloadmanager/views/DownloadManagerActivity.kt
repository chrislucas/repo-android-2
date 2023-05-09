package com.example.feature.network.downloadfile.downloadmanager.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidallcodelabs.R

/**
 * https://developer.android.com/reference/android/app/DownloadManager
 * https://www.geeksforgeeks.org/how-to-download-file-from-url-in-android-programmatically-using-download-manager/
 */
class DownloadManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_manager)
    }
}