package com.br.justcomposelabs.utils.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.CreationExtras


/*
    Create ViewModels with dependencies
    https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-factories
 */


object ViewModelFactories {


    fun modernFactory(): ViewModelProvider.Factory {
        // https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-factories
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                return super.create(modelClass, extras)
            }
        }
    }

    /**
     * @sse  ViewModelStore
     */
    @JvmStatic
    fun <V: ViewModel> create(
        viewModelStore: ViewModelStore,
        factory: ViewModelProvider.Factory,
        ref: Class<V>
    ): V {
        return ViewModelProvider(viewModelStore, factory).get(ref)
    }

}