package com.experience.tutorial.aboutsharedflow.starter.domain.model

import android.os.Build
import com.experience.tutorial.aboutsharedflow.starter.domain.fetch
import java.math.BigDecimal
import java.time.Instant

class CoinHistory private constructor(val pricesOverTime: Map<Instant, BigDecimal>) {

    companion object {
        fun of(mapping: Map<String?, String?>) = fetch {
            CoinHistory(
                mapping

                    .filter { (k, v) ->
                        k != null && v != null
                    }.map {
                        /*

                            Call requires API level 26 (current min is 21): java.time.Instant#parse
                            https://developer.android.com/reference/java/time/Instant
                         */
                        /**
                         * Desugaring library
                         * https://stackoverflow.com/questions/55550936/call-requires-api-level-26-current-min-is-23-java-time-instantnow
                         * coreLibraryDesugaring 'com.android.tools:desugar_jdk_libs:1.0.5'
                         * https://developer.android.com/studio/write/java8-support-table
                         *
                         *  Como configurar a api desugaring support
                         *  https://developer.android.com/studio/write/java8-support#library-desugaring
                         */
                        Instant.parse(it.key) to BigDecimal(it.value)

                    }.toMap()
            )
        }
    }
}