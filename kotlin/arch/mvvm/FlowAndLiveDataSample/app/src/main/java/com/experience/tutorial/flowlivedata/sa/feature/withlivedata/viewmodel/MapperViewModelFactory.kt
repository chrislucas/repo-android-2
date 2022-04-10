package com.experience.tutorial.flowlivedata.sa.feature.withlivedata.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MapperViewModelFactory(private val argsAndValues: Map<Class<*>, Array<*>> = emptyMap()) : ViewModelProvider.Factory{

    constructor(arg: Class<*>, value: Any): this(mapOf(arg to arrayOf(value)))

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val keys = argsAndValues.keys.toTypedArray()
        val values = argsAndValues.values.toTypedArray()
        return if (values.size == 1) {
            modelClass.getConstructor(*keys).newInstance(values[0])
        } else {
            modelClass.getConstructor(*keys).newInstance(*values)
        }
    }
}