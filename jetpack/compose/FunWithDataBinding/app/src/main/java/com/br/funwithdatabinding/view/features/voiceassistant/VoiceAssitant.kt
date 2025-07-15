package com.br.funwithdatabinding.view.features.voiceassistant

import android.content.Context
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer

/*
    https://source.android.com/docs/automotive/voice/ttr
    https://developer.android.com/develop/devices/assistant/overview

    appactions-fitness-kotlin
    https://github.com/actions-on-google/appactions-fitness-kotlin

    Add Voice Commands to Android Apps
    https://medium.com/geekculture/add-voice-commands-to-android-apps-80157c0d5bcc

    Building Voice-Activated Assistants in Android Apps
    https://www.linkedin.com/pulse/building-voice-activated-assistants-android-apps-shubham-sorathiya-9sqwf/
 */



fun Context.creatSspeechRecoginizer(listener: RecognitionListener = object: RecognitionListener {
    override fun onReadyForSpeech(params: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onBeginningOfSpeech() {
        TODO("Not yet implemented")
    }

    override fun onRmsChanged(rmsdB: Float) {
        TODO("Not yet implemented")
    }

    override fun onBufferReceived(buffer: ByteArray?) {
        TODO("Not yet implemented")
    }

    override fun onEndOfSpeech() {
        TODO("Not yet implemented")
    }

    override fun onError(error: Int) {
        TODO("Not yet implemented")
    }

    override fun onResults(results: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onPartialResults(partialResults: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
        TODO("Not yet implemented")
    }

}) =
    SpeechRecognizer.createSpeechRecognizer(this).setRecognitionListener(listener)
