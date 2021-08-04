package com.br.words.views.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GenericViewModelFactory(
    private val argumentsOfConstructor: Array<Class<*>>,
    private val valuesOfEachArgument: Array<*>
) :
    ViewModelProvider.Factory {


    constructor(argumentOfConstructor: Class<*>, valueOfArgument: Any) :
            this(arrayOf(argumentOfConstructor), arrayOf(valueOfArgument))

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val constructor = modelClass.getConstructor(*argumentsOfConstructor)
        return if (valuesOfEachArgument.size > 1) {
            constructor.newInstance(valuesOfEachArgument)
        } else if (valuesOfEachArgument.size == 1) {
            constructor.newInstance(valuesOfEachArgument[0])
        } else {
            throw IllegalArgumentException("")
        }
    }

}