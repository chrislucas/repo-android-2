package com.experience.tutorial.flowlivedata.sa.feature.utils.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

object ProviderViewModel {

    @JvmStatic
    fun <V : ViewModel> provide(
        viewModelStore: ViewModelStore,
        factory: ViewModelProvider.Factory,
        clazz: Class<V>
    ): V = ViewModelProvider(viewModelStore, factory)[clazz]
}
