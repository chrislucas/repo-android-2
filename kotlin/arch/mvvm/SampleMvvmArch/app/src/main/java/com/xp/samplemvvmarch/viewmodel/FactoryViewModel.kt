package com.xp.samplemvvmarch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FactoryViewModel(private vararg val args: Any) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Any::class.java).newInstance(args)
    }
}