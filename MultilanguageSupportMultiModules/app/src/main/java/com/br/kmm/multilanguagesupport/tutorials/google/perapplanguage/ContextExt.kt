package com.br.kmm.multilanguagesupport.tutorials.google.perapplanguage

import android.app.LocaleManager
import android.content.Context
import android.os.LocaleList
import java.util.Locale

/*
    https://developer.android.com/guide/topics/resources/app-languages#framework-impl
 */

fun Context.setApplicationLocales(acronym: String) {
    getSystemService(LocaleManager::class.java).applicationLocales =
        LocaleList(Locale.forLanguageTag(acronym))
}

fun Context.currentAppLocales() = getSystemService(LocaleManager::class.java).applicationLocales