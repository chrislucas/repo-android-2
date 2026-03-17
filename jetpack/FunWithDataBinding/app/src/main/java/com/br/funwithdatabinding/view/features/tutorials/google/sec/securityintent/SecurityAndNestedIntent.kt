package com.br.funwithdatabinding.view.features.tutorials.google.sec.securityintent

import android.content.Intent

/*
    TODO verificar a possibilidade de tornar todas as intents exported=false
    TODO verificar como abrir uma intent via deeplink com exported=false
    TODO verificar como é possivel checar a origem de uma Intent


    Intent redirection
    https://developer.android.com/privacy-and-security/risks/intent-redirection

    // Android Nesting Intents
    // https://medium.com/androiddevelopers/android-nesting-intents-e472fafc1933

    Check the origin of your Android Deep Links
    https://medium.com/android-news/check-the-origin-of-your-deep-links-android-intent-e9125e4dd29e

    Intents and intent filters
    https://developer.android.com/guide/components/intents-filters

    Android Intent : A Comprehensive Guide with Examples
    https://rommansabbir.com/android-intent-a-comprehensive-guide-with-examples

    https://developer.android.com/develop/background-work/services?hl=pt-br

    IntentService e WorkManager - Background Execution Limits
    https://developer.android.com/about/versions/oreo/background
    https://developer.android.com/reference/androidx/work/WorkManager

    Background tasks overview
    https://developer.android.com/develop/background-work/background-tasks

    Understanding Service and IntentService in Android with Kotlin
    https://medium.com/@fierydinesh/understanding-service-and-intentservice-in-android-with-kotlin-cea76512ec16


 */


fun Intent.isFromAllowedOrigin(checkOrigin: (origin: String?) -> Boolean) = checkOrigin(getPackage())