package com.br.funwithtensorflowlite.tutorial.google.tensorflow.litemodel.digitclassifier.start.service

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.util.concurrent.Executors

class DigitClassifier(private val ctx: Context) {

    private var interpreter: Interpreter? = null

    var isInitialized = false
        private set


    private val executorService = Executors.newCachedThreadPool()
}