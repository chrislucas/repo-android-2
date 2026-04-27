package com.br.justcomposelabs.tutorial.google.view.handleconfigurationchanges

import android.content.res.Configuration
import android.content.res.Configuration.COLOR_MODE_WIDE_COLOR_GAMUT_UNDEFINED
import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.justcomposelabs.tutorial.google.lazyrowmaxheight.items

/*
    https://developer.android.com/guide/topics/resources/runtime-changes

    - Algumas configuracoes que podem mudar enquanto o app está rodando
        - App display Size
        - Screen orientation
        - Font size and weight
        - Locale
        - Dark/Light mode
        - Keyboard availability


        - A maioria dessas configuracoes mudam devido alguma interecao do usuario.
            - Alterar configuracoes do dispositivo como tamanho da fonte, linguagem,
            preferencia de temas e seus respectivo valores no objeto Configuration

            - Configuration: https://developer.android.com/reference/android/content/res/Configuration
                -
 */

@Preview(showSystemUi = true)
@Composable
fun ConfigurationDeviceInfoComponent() {
    /*
        https://developer.android.com/reference/android/content/res/Configuration
        - Essa classe descreve todas as configuracoes do device que podem impactar
        os recursos que aplicacao recupera
            - configuracao especificada pelo usuario (locale list, scaling)
     */
    val config = LocalResources.current.configuration

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding(),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Locales",
            textAlign = TextAlign.Center,
            style =
            TextStyle(
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
            ),
        )

        LazyColumn {
            items(config.locales.size()) { idx ->
                config.locales.get(idx)?.let { locale ->
                    Text(text = "Locale: ${locale.displayName}")
                }
            }
        }

        HorizontalDivider(
            thickness = 2.dp,
            modifier = Modifier.padding(vertical = 8.dp),
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Screen Info",
            textAlign = TextAlign.Center,
            style =
            TextStyle(
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
            ),
        )
        config.run {
            Text(
                text = "Font Scale: $fontScale",
                modifier = Modifier.padding(bottom = 4.dp),
            )

            Text(
                text = "Screen Height: $screenHeightDp dp",
                modifier = Modifier.padding(bottom = 4.dp),
            )

            Text(
                text = "Density DPI: ${this.densityDpi}",
                modifier = Modifier.padding(bottom = 4.dp),
            )

            Text(
                text = "Screen Width: $screenWidthDp dp",
                modifier = Modifier.padding(bottom = 4.dp),
            )

            val orientationConfigured =
                when (orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> "Landscape"
                    Configuration.ORIENTATION_PORTRAIT -> "Portrait"
                    else -> "Undefined"
                }

            Text(
                text = "Orientation: $orientationConfigured",
                modifier = Modifier.padding(bottom = 4.dp),
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                Text(
                    text = "Font Weight Adjustment: ${this.fontWeightAdjustment}",
                    modifier = Modifier.padding(bottom = 4.dp),
                )

                val colorModeConfigured =
                    when (colorMode) {
                        Configuration.COLOR_MODE_WIDE_COLOR_GAMUT_YES -> "Wide Color Gamut"
                        Configuration.COLOR_MODE_HDR_YES -> "HDR"
                        COLOR_MODE_WIDE_COLOR_GAMUT_UNDEFINED -> "Wide Color Gamut Undefined"
                        else -> "Undefined"
                    }
                Text(
                    text = "Color Mode: $colorModeConfigured | $colorMode",
                    modifier = Modifier.padding(bottom = 4.dp),
                )

                Text(
                    text = "Is Screen Wide Color Gamut: $isScreenWideColorGamut",
                    modifier = Modifier.padding(bottom = 4.dp),
                )
            }
        }

        HorizontalDivider(
            thickness = 2.dp,
            modifier = Modifier.padding(vertical = 8.dp),
        )
    }
}
