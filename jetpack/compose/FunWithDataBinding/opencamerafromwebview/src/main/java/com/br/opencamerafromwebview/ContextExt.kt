package com.br.opencamerafromwebview

import android.content.Context
import androidx.annotation.RawRes

fun Context.readRaw(@RawRes id: Int) =
    resources.openRawResource(id).bufferedReader().readText()