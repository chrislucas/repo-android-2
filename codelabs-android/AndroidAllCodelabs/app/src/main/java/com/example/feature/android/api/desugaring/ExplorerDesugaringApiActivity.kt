package com.example.feature.android.api.desugaring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidallcodelabs.R
import com.example.androidallcodelabs.databinding.ActivityExplorerDesugaringApiBinding
import java.time.Clock
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * https://www.linkedin.com/posts/qamar-elsafadi_desugaring-in-androidpdf-activity-7059969361226178561-50Xb/
 *
 * https://android-developers.googleblog.com/2023/02/api-desugaring-supporting-android-13-and-java-nio.html
 *
 * https://developer.android.com/studio/write/java8-support?hl=pt-br#library-desugaring
 * Suporte a simplificacao de AI do Java 8+ (Plugin para gradle 4.0.0+)

    "Para oferecer suporte a essas APIs de linguagem, o plug-in compila um
    arquivo DEX separado que contém uma implementação das APIs ausentes e
    as inclui no app. O processo de simplificação reescreve o código do
    app para usar essa biblioteca durante a execução."

 */

class ExplorerDesugaringApiActivity : AppCompatActivity() {

    val bind: ActivityExplorerDesugaringApiBinding by lazy {
        ActivityExplorerDesugaringApiBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        /**
         * Sem o uso da api desugaring recebemos um warning
         *
         * Call requires API level 26 (current min is 24): java.time.Clock#systemUTC
         */
        val date = LocalDateTime.now(Clock.systemUTC())
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            .withZone(ZoneId.systemDefault())

        bind.tvFormattedDate.text = formatter.format(date)
    }
}