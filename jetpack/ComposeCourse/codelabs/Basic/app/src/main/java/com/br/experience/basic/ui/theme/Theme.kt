package com.br.experience.basic.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/**
 * https://developer.android.com/codelabs/jetpack-compose-basics#3
 *
 * No exemplo que estamos contruindo, mudamos a cor de fundo de um texto
 * para a cor primaria, no caso azul, e o texto foi mudado para a cor branca
 * sem termos que definir. Isso ocrreu porque os componentes da biblioteca
 * Material, tal como androidx.compose.material.Surface sao construidos
 * para fornecer a melhor experiencia para o usuario, tomando conta de caracteristicas
 * que provavelmente queremos em nosso app (constraste entre cor de fonte e fundo)
 *
 * No caso do exemplo acima, o componente Surface entende que, quano a cor de fundo
 * e definida como primary, qualquer texto sob o componente deve ter a cor definina
 * pelo atributo onPrimary, que é definido no tema.
 */
private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    surface = Blue,
    onSurface = Navy,
    onPrimary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    surface = Blue,
    onSurface = Color.White

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)


@Composable
fun BasicTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}