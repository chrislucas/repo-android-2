package com.br.opencamerafromwebview

import android.content.Intent

interface CaptureImage {
    fun createIntentCaptureImage(): Intent?
}