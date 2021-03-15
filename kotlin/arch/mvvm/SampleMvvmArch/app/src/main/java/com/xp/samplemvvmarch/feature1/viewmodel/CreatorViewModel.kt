package com.xp.samplemvvmarch.feature1.viewmodel

import androidx.lifecycle.ViewModel

interface CreatorViewModel<T: ViewModel?> {

    fun create() : T

}
