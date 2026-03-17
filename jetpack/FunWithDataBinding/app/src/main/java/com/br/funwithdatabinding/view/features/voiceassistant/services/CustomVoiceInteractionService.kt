package com.br.funwithdatabinding.view.features.voiceassistant.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

/*
    TODO
    Atributos de uma service
    https://developer.android.com/guide/topics/manifest/service-element#proc
 */
class CustomVoiceInteractionService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}