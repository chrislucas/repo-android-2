package com.br.experience.funmobdatascience.features.share.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.br.experience.funmobdatascience.features.share.repositories.ShareRepository
import com.br.experience.funmobdatascience.utils.viewmodel.Operation
import com.br.experience.funmobdatascience.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListShareViewModel(private val repository: ShareRepository) : BaseViewModel() {

    private val stateFindInvestmentAssets = MutableStateFlow<Operation<*>>(Operation.idle<Nothing>())

    val observableSateFindInvAssets: StateFlow<Operation<*>> = stateFindInvestmentAssets

    fun findAssets() =
        viewModelScope.launch {
            repository.getAssets()
                .catch { exception ->
                   Log.e("", "$exception")
                }.collect {

                }
        }
}