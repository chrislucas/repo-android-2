package com.xp.samplemvvmarch.viewmodel

import androidx.lifecycle.ViewModel

interface CreatorViewModel<T: ViewModel?> {

    fun create() : T

}
