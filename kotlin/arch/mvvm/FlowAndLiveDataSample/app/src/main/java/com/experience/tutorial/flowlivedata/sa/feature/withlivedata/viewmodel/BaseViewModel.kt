package com.experience.tutorial.flowlivedata.sa.feature.withlivedata.viewmodel

import androidx.lifecycle.ViewModel
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

open class BaseViewModel() : ViewModel(), CoroutineScope{
    private val viewModelSupervisorJob = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + viewModelSupervisorJob

    override fun onCleared() {
        super.onCleared()
        viewModelSupervisorJob.cancelChildren()
    }
}