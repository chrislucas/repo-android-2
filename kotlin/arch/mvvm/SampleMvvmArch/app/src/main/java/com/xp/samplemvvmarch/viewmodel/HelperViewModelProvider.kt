package com.xp.samplemvvmarch.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

object HelperViewModelProvider {

    @JvmStatic
    fun <V : ViewModel> of(clazz: Class<V>, fragment: Fragment): ViewModel =
        ViewModelProviders.of(fragment).get(clazz)

    @JvmStatic
    fun <V: ViewModel> of(fragment: Fragment, factoryViewModel: ViewModelProvider.Factory, clazz: Class<V>): V {
        return ViewModelProviders.of(fragment, factoryViewModel).get(clazz)
    }
}