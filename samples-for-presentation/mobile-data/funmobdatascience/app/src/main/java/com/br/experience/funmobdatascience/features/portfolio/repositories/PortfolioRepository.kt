package com.br.experience.funmobdatascience.features.portfolio.repositories

import com.br.experience.funmobdatascience.features.portfolio.models.Portfolio
import com.br.experience.funmobdatascience.features.shares.http.InvestmentAssetApi
import com.br.experience.funmobdatascience.utils.network.ExecuteSafeOperation
import com.br.experience.funmobdatascience.utils.viewmodel.Operation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PortfolioRepository(private val api: InvestmentAssetApi) {

    suspend fun getPortfolio(userId: String, portfolioId: String): Flow<Operation<Portfolio>> =
        flow {
            val op = {
                api.getPortfolio(userId, portfolioId)
            }
            val result = ExecuteSafeOperation.safeRequest(op) { response ->
                "${response.errorBody()}"
            }
            emit(result)
        }
}