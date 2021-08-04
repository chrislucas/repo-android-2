package com.br.start.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GenericViewModelFactory(private val map: Map<Class<*>, Any>) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val argumentTypes = map.keys.toTypedArray()
        val valuesEachArg = map.values.toTypedArray()
        return modelClass.getConstructor(*argumentTypes).newInstance(valuesEachArg)
    }
}


fun <T : ViewModel, A> buildViewModel(constructor: (A) -> T):
            (A) -> ViewModelProvider.NewInstanceFactory {
    return { arguments ->
        object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return constructor(arguments) as T
            }
        }
    }
}


fun <T : ViewModel> getViewModelFactory(constructor: (Array<Any>) -> T):
            (Array<Any>) -> ViewModelProvider.NewInstanceFactory {
    return { arguments ->
        object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return constructor(arguments) as T
            }
        }
    }
}


fun <T : ViewModel> getViewModelFactory(constructor: (Map<Class<Any>, Any>, Class<out ViewModel?>) -> T):
            (Map<Class<Any>, Class<out ViewModel?>>) -> ViewModelProvider.NewInstanceFactory {
    return { arguments ->
        object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return constructor(arguments, modelClass) as T
            }
        }
    }
}

