package com.br.funrestapi.utils.viewmodel

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object ProviderViewModel {

    @JvmStatic
    fun <V : ViewModel> ComponentActivity.createViewModel(factory: ViewModelProvider.Factory, clazz: Class<V>) =
        ViewModelProvider(viewModelStore, factory)[clazz]

    @JvmStatic
    fun <V : ViewModel> ComponentActivity.createViewModel(clazz: Class<V>) =
        ViewModelProvider(viewModelStore, MapperViewModelFactory())[clazz]
}
