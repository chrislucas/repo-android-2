package com.br.justcomposelabs.tutorial.coroutines.kdoc.flow.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.launch
import timber.log.Timber

fun nonInjectDispatcher() {
    CoroutineScope(Dispatchers.Default).launch {
        val flowSampleViewModel = FlowSampleViewModel(200L)
        flowSampleViewModel.sample.collect { value ->
            Timber.tag("FlowSample").d("Collected value: $value")
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun globalScopeSample() {
    /*
        Método só para testar o detekt
     */
    GlobalScope.launch {
        val flowSampleViewModel = FlowSampleViewModel(200L)
        flowSampleViewModel.sample.collect { value ->
            Timber.tag("FlowSample").d("Collected value: $value")
        }
    }
}

/*
    https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/sample.html
 */
class FlowSampleViewModel(private val periodMillis: Long) : ViewModel() {
    @OptIn(FlowPreview::class)
    val sample = flow {
        repeat(100000) {
            emit(it)
            val randomDelay = (100L..200L).random()
            val message = "Emitting $it with delay $randomDelay ms"
            Timber.tag("ViewModelFlowSample").d(message)
            delay(randomDelay)
            emit(message)
        }
    }.sample(periodMillis)

    companion object {
        val KEY_PERIOD_MILLIS = object : CreationExtras.Key<Long> {}

        val FACTORY = viewModelFactory {
            initializer {
                val periodMillis = this[KEY_PERIOD_MILLIS] ?: 200L
                FlowSampleViewModel(periodMillis)
            }
        }
    }
}
