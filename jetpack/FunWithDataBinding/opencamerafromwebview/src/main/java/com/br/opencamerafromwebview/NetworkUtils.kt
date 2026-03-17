package com.br.opencamerafromwebview

import android.content.Context
import android.net.ConnectivityManager

fun Context.test() {
    val info = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    info.activeNetwork?.let {

    }

}