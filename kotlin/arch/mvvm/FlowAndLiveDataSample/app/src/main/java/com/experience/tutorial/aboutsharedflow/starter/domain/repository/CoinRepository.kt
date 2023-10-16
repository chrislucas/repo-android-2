package com.experience.tutorial.aboutsharedflow.starter.domain.repository

import com.experience.tutorial.aboutsharedflow.starter.domain.model.Coin
import com.experience.tutorial.aboutsharedflow.starter.domain.model.CoinHistory

interface CoinRepository {
    suspend fun getCoins(): Result<List<Coin>>
    suspend fun getHistoryCoin(coinId: String): Result<CoinHistory>
}