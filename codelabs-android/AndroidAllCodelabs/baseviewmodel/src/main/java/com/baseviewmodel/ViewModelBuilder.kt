package com.baseviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

object ViewModelBuilder {

    @JvmStatic
    fun <V : ViewModel> build(
        viewModelStore: ViewModelStore,
        factory: ViewModelProvider.Factory,
        clazz: Class<V>
    ): V = ViewModelProvider(viewModelStore, factory)[clazz]
}