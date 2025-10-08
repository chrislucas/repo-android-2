package com.br.justcomposelabs.tutorial.google.connectivity

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient

/*
    TODO
    https://developer.android.com/develop/connectivity/avoid-unoptimized-downloads
 */



fun Context.cachedOkHttp(cacheSize: Long = 10 * 1024 * 1024) =
    OkHttpClient.Builder()
        .cache(Cache(cacheDir, cacheSize))
        .build()