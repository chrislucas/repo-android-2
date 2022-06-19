package com.br.experience.funmobdatascience.viewmodel

import androidx.lifecycle.viewModelScope
import com.br.experience.funmobdatascience.repositories.InvestmentAssetRepository
import com.br.experience.funmobdatascience.utils.viewmodel.Operation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListAssetsViewModel(private val repository: InvestmentAssetRepository) : BaseViewModel() {

    private val stateFindInvestmentAssets = MutableStateFlow<Operation<*>>(Operation.idle<Nothing>())

    val observableSateFindInvAssets: StateFlow<Operation<*>> = stateFindInvestmentAssets

    fun findAssets() =
        viewModelScope.launch {
            repository.getAssets()
                .catch { exception ->

                }.collect {

                }
        }
}