package com.br.experience.funmobdatascience.repositories

import com.br.experience.funmobdatascience.models.Share
import com.br.experience.funmobdatascience.network.InvestmentAssetApi
import com.br.experience.funmobdatascience.utils.network.ExecuteSafeOperation
import com.br.experience.funmobdatascience.utils.viewmodel.Operation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class InvestmentAssetRepository(private val api: InvestmentAssetApi) {

    suspend fun getAssets(): Flow<Operation<List<Share>?>> {
        return flow {
            val result: Operation<List<Share>?> =
                ExecuteSafeOperation.safeRequest(api::getAssets) { response -> "${response.errorBody()}" }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}