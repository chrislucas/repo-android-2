package com.br.experience.funmobdatascience.features.portfolio.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.br.experience.funmobdatascience.BuildConfig
import com.br.experience.funmobdatascience.features.portfolio.models.Portfolio
import com.br.experience.funmobdatascience.features.portfolio.repositories.PortfolioRepository
import com.br.experience.funmobdatascience.network.ProviderEndpointClient
import com.br.experience.funmobdatascience.network.interceptor.samples.HttpInterceptor
import com.br.experience.funmobdatascience.utils.models.Operation
import com.br.experience.funmobdatascience.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PortfolioViewModel : BaseViewModel() {

    private val stateGetPortfolio = MutableStateFlow<Operation<Portfolio>>(Operation.idle<Nothing>())

    val observableStateGetPortfolio: StateFlow<Operation<Portfolio>> = stateGetPortfolio

    private val httpInterceptor = HttpInterceptor()
    private val client = ProviderEndpointClient.provideInvestmentAssetApi(httpInterceptor)
    private val repository: PortfolioRepository = PortfolioRepository(client)

    fun getPortfolio(userId: String, portfolioId: String) =
        viewModelScope.launch {
            repository.getPortfolio(userId, portfolioId)
                .catch {
                    if (BuildConfig.DEBUG) {
                        Log.e("", "$this")
                    }
                }.collect { operation ->
                    stateGetPortfolio.emit(operation)
                }
        }
}