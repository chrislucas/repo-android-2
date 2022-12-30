package com.br.experience.funmobdatascience.features.shares.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.br.experience.funmobdatascience.BuildConfig
import com.br.experience.funmobdatascience.features.shares.models.Share
import com.br.experience.funmobdatascience.features.shares.repositories.ShareRepository
import com.br.experience.funmobdatascience.utils.models.Operation
import com.br.experience.funmobdatascience.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SharesViewModel(private val repository: ShareRepository) : BaseViewModel() {

    private val stateFindInvestmentAssets = MutableStateFlow<Operation<List<Share>?>>(Operation.idle<Nothing>())

    val observableSateFindInvAssets: StateFlow<Operation<List<Share>?>> = stateFindInvestmentAssets

    fun findAssets() =
        viewModelScope.launch {
            repository.getAssets()
                .catch { exception ->
                    if (BuildConfig.DEBUG) {
                        Log.e("", "$exception")
                    }
                }.collect { operation ->
                    stateFindInvestmentAssets.emit(operation)
                }
        }
}