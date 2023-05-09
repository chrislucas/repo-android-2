package com.baseviewmodel

import androidx.lifecycle.ViewModel
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val supervisorJob = SupervisorJob()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + supervisorJob

    override fun onCleared() {
        super.onCleared()
        supervisorJob.cancelChildren()
    }
}