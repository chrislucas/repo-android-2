package com.experience.tutorial.aboutsharedflow.starter.domain.model

import com.experience.tutorial.aboutsharedflow.starter.domain.fetch
import java.math.BigDecimal
import java.util.Locale

data class Coin (
    val id: String,
    val symbol: String,
    val name: String,
    val supply: BigDecimal,
    val marketCapUsd: BigDecimal,
    val priceUsd: BigDecimal,
    val changePercent24Hr: Float,
    val image: String
) {
    companion object {
        fun of(context: CoinContext) = context.fetchCoin()
    }
}

class CoinContext(
    private val id: String?,
    private val symbol: String?,
    private val name: String?,
    private val supply: String?,
    private val marketCapUsd: String?,
    private val priceUsd: String?,
    private val changePercent24Hr: String?
) {

    companion object {
        const val EMPTY_CHANGE_PERCENT = -1f
        val EMPTY_MARKET_CAP: BigDecimal = BigDecimal.ZERO
        val EMPTY_SUPPLY: BigDecimal = BigDecimal.ZERO

        private const val IMAGES_ENDPOINT = "https://static.coincap.io/assets/icons/"
        private const val IMAGES_SUFFIX = "@2x.png"
    }

    fun fetchCoin() = fetch {
        requireNotNull(id)
        require(id.isNotBlank())

        requireNotNull(symbol)
        require(symbol.isNotBlank())

        requireNotNull(name)
        require(name.isNotBlank())

        requireNotNull(priceUsd)
        require(priceUsd.isNotBlank())

        Coin(
            id,
            symbol,
            name,
            supply = supply?.toBigDecimal() ?: EMPTY_SUPPLY,
            marketCapUsd = marketCapUsd?.toBigDecimal() ?: EMPTY_MARKET_CAP,
            priceUsd = priceUsd.toBigDecimal(),
            changePercent24Hr = changePercent24Hr?.toFloat() ?: EMPTY_CHANGE_PERCENT,
            image = "$IMAGES_ENDPOINT${symbol.lowercase(Locale.getDefault())}$IMAGES_SUFFIX"
        )
    }
}