package com.br.datastore.tutorials.utils.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.coroutines.CoroutineContext

fun <V : ViewModel> createViewModel(
    viewModelStore: ViewModelStore,
    factory: ViewModelProvider.Factory,
    clazz: Class<V>
): V = ViewModelProvider(viewModelStore, factory)[clazz]


class ViewModelFactory(private val argsToValues: Map<Class<*>, List<*>> = emptyMap()) :
    ViewModelProvider.Factory {

    // constructor(arg: Class<*>, value: Any) : this(mapOf(arg to listOf(value)))

    /**
     * @see  androidx.lifecycle.viewmodel.ViewModelInitializer
     * @see  androidx.lifecycle.ViewModelProvider.Factory.Companion
     * https://developer.android.com/reference/androidx/lifecycle/viewmodel/ViewModelInitializer
     */

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (argsToValues.isEmpty()) {
            modelClass.getConstructor().newInstance()
        } else {
            val args = argsToValues.keys.toTypedArray()
            val values = argsToValues.values.toTypedArray()
            modelClass.getConstructor(*args).newInstance(*values)
        }
    }
}


abstract class BaseViewModel : ViewModel(), CoroutineScope {
    private val viewModelSupervisorJob = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + viewModelSupervisorJob

    override fun onCleared() {
        super.onCleared()
        viewModelSupervisorJob.cancelChildren()
    }
}


abstract class BaseViewModelHandlerException(
    private val handler: (CoroutineContext, Throwable) -> Unit = { c, t -> }
) :
    ViewModel() {

    private val viewModelSupervisorJob = SupervisorJob()

    private val mutableStateCoroutineException = MutableStateFlow<Throwable?>(null)

    val stateCoroutineException = mutableStateCoroutineException.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { c, t ->
        handler(c, t)
        mutableStateCoroutineException.value = t
    }

    private val scope = CoroutineScope(Dispatchers.Main + Job() + exceptionHandler)

    private val scopeWithSupervisedJob = CoroutineScope(Dispatchers.Main + SupervisorJob() + exceptionHandler)

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
        scopeWithSupervisedJob.coroutineContext.cancelChildren()
    }

}