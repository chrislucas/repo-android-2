package com.br.justcomposelabs.utils.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/*
    https://developer.android.com/codelabs/kotlin-coroutines#1
    https://github.com/android/codelab-kotlin-coroutines/blob/master/coroutines-codelab/start/src/main/AndroidManifest.xml
 */

class ViewModelDependencyFactory(private val args: Map<Class<*>, List<*>> = emptyMap()) :
    ViewModelProvider.Factory {

    constructor(singleArg: Class<*>, value: Any) : this(mapOf(singleArg to listOf(value)))

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (args.isEmpty()) {
            modelClass.getConstructor().newInstance()
        } else {
            val dependency = args.keys.toTypedArray()
            val eachDependency = args.values.toList()
            modelClass.getConstructor(*dependency).newInstance(eachDependency)
            /*
                when (eachDependency.size) {
                    1 -> {
                        test(modelClass, dependency, eachDependency)
                    }
                    else -> {
                        modelClass.getConstructor(*dependency).newInstance(eachDependency)
                    }
                }
             */
        }
    }

    private fun <T : ViewModel> test(
        modelClass: Class<T>,
        dependency: Array<Class<*>>,
        eachDependency: List<List<*>>
    ): T {
        return if (eachDependency[0].isEmpty()) {
            modelClass.getConstructor().newInstance()
        } else {
            modelClass.getConstructor(*dependency).newInstance(eachDependency[0][0])
        }
    }
}