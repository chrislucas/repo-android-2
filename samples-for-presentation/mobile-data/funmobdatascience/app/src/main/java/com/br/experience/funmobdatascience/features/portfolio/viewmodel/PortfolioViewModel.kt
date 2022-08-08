package com.br.experience.funmobdatascience.features.portfolio.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.br.experience.funmobdatascience.BuildConfig
import com.br.experience.funmobdatascience.features.portfolio.models.Portfolio
import com.br.experience.funmobdatascience.features.portfolio.repositories.PortfolioRepository
import com.br.experience.funmobdatascience.utils.viewmodel.Operation
import com.br.experience.funmobdatascience.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PortfolioViewModel(private val repository: PortfolioRepository) : BaseViewModel() {

    private val stateGetPortfolio = MutableStateFlow<Operation<Portfolio>>(Operation.idle<Nothing>())

    val observableStateGetPortfolio: StateFlow<Operation<Portfolio>> = stateGetPortfolio

    fun getPortfolio(userId: String, portfolioId: String) =
        viewModelScope.launch {
            repository.getPortfolio(userId, portfolioId)
                .catch { exception ->
                    if (BuildConfig.DEBUG) {
                        Log.e("", "$exception")
                    }
                }.collect { operation ->
                    stateGetPortfolio.emit(operation)
                }
        }
}