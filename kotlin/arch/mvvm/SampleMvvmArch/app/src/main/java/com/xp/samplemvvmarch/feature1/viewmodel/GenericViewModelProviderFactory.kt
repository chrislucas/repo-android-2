package com.xp.samplemvvmarch.feature1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class GenericViewModelProviderFactory(private val types: Array<Class<*>>, private val args: Array<Any>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(*types).newInstance(*args)
    }
}