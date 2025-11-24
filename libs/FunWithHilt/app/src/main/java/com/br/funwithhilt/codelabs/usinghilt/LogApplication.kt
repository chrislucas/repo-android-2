package com.br.funwithhilt.codelabs.usinghilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
    https://developer.android.com/codelabs/android-hilt#3

    - HiltAndroidApp
        - Dispara geracao de codigo pelo hilt, incluindo base classes para application
        que pode usar Injecacao de dependencia.

        - Application container é o container pai do app, o que significa que outros
        container podem acessar as dependencias que Application fornece

    -  Depois que o Hilt esta configurado numa classe que extende a Application
    e um Application-level component esta disponivel, Hilt pode prover depdendencias para outras
    classes Android que tem a anotacao AndroidEntryPoint
        - Annotacoes suportadas
            - Application - HiltAndroidApp
            - View Model - @HiltViewModel
            - Activity
            - Fragment
            - View
            - Service
            - BroadcastReceiver


 */

@HiltAndroidApp
class LogApplication: Application() {
}