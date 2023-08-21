package com.example.feature.customview.pdfreader.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidallcodelabs.R

/**
 *  Displaying PDF in ViewPager2
 *  https://medium.com/mobile-app-development-publication/displaying-pdf-in-viewpager2-f0778eac7aa3
 *
 *  PdfRenderer
 *  https://developer.android.com/reference/android/graphics/pdf/PdfRenderer
 *
 *  ParcelFileDescriptor
 *  https://developer.android.com/reference/android/os/ParcelFileDescriptor
 *  https://stackoverflow.com/questions/6715898/what-is-parcelfiledescriptor-in-android
 */

class PdfReaderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_reader)
    }
}