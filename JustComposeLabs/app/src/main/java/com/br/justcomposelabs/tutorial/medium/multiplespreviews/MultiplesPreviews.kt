package com.br.justcomposelabs.tutorial.medium.multiplespreviews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.R
import com.br.justcomposelabs.utils.composable.paddingEdgeToEdge
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

/*
    https://medium.com/@kyle.dahlin0/jetpack-compose-multipreview-setup-e9748b3dc86c
    - Regras
        - o Artigo deixa uma sugestao de regras interessante de como usar essas anotacoes
            - Para conteudo de uma página completa
                - Device, Language
            - Para conteudo grafico ou texto que está alinhado a direita ou esquerda do layout
                - Language
            - Conteudo com texto largo ou componentes de multiplos textos
                - Language, FontScale

    Create custom multipreview annotations
    https://developer.android.com/develop/ui/compose/tooling/previews#preview-multipreview
 */


const val LANGUAGES = "LANGUAGES"
@Preview(
    locale = "pt",
    showBackground = true,
    name = "Portuguese",
    group = LANGUAGES,
    showSystemUi = false
)
@Preview(
    locale = "es",
    showBackground = true,
    name = "Spanish",
    group = LANGUAGES,
    showSystemUi = false
)
@Preview(locale = "ko", showBackground = true, name = "Korean", group = LANGUAGES)
@Preview(locale = "ar", showBackground = true, name = "Arabic", group = LANGUAGES)
annotation class LocalePreviews

const val FONT_SCALE = "FONT_SCALE"
@Preview(
    fontScale = 0.85f,
    group = FONT_SCALE,
    showBackground = true
)
@Preview(
    fontScale = 1f,
    group = FONT_SCALE,
    showBackground = true
)
@Preview(
    fontScale = 3f,
    group = FONT_SCALE,
    showBackground = true
)
annotation class FontScalePreviews

private const val DEVICES = "DEVICES"
@Preview(
    name = "Phone_PIXEL_2",
    group = DEVICES,
    device = Devices.PIXEL_2,
    showSystemUi = true
)
@Preview(
    name = "Tablet_PIXEL_C",
    group = DEVICES,
    device = Devices.PIXEL_C,
    showSystemUi = true
)
annotation class DevicePreviews

@LocalePreviews
@FontScalePreviews
@Composable
fun Greeting() {
    JustComposeLabsTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paddingEdgeToEdge(),
        ) {
            Text(text = stringResource(R.string.greeting))
        }
    }
}
