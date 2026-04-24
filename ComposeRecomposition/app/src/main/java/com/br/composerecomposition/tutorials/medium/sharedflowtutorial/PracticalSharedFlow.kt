package com.br.composerecomposition.tutorials.medium.sharedflowtutorial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.composerecomposition.tutorials.utils.currentHour
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/*
    https://proandroiddev.com/sharedflow-practical-examples-591f70e27711
 */
class ViewModelSharedFlowExample : ViewModel() {
    val sharedFlow = MutableSharedFlow<String>()

    init {
        viewModelScope.launch {
            launch {
                while (true) {
                    // thread safe
                    sharedFlow.emit(currentHour())
                    delay(1000)
                }
            }
        }
    }
}
