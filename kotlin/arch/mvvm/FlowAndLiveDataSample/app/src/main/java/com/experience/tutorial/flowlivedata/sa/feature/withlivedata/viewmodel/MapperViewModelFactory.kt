package com.experience.tutorial.flowlivedata.sa.feature.withlivedata.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MapperViewModelFactory(private val argsAndValues: Map<Class<*>, List<*>> = emptyMap()) : ViewModelProvider.Factory{

    constructor(arg: Class<*>, value: Any): this(mapOf(arg to listOf(value)))

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (argsAndValues.isEmpty()) {
            modelClass.getConstructor().newInstance()
        } else {
            val keys : Array<Class<*>> = argsAndValues.keys.toTypedArray()
            val values : List<List<*>> = argsAndValues.values.toList()
            when (values.size) {
                1 -> {
                    if (values[0].isEmpty()) {
                        modelClass.getConstructor().newInstance()
                    } else {
                        modelClass.getConstructor(*keys).newInstance(values[0][0])
                    }
                }
                else -> {
                    modelClass.getConstructor(*keys).newInstance(values)
                }
            }
        }
    }
}