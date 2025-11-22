package com.br.funwithhilt.codelabs.usinghilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
    https://developer.android.com/training/dependency-injection/hilt-android#application-class

    A anotacao HiltAndroidApp dispara o gerador de code Hilt
 */
class LogApplication: Application()