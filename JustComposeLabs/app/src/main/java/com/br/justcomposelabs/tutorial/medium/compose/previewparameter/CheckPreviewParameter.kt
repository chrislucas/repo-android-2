package com.br.justcomposelabs.tutorial.medium.compose.previewparameter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import com.br.justcomposelabs.tutorial.google.compose.preview.largedataset.User
import com.br.justcomposelabs.tutorial.google.compose.preview.largedataset.UserPreviewParameterProvider

/*
    https://foso.github.io/Jetpack-Compose-Playground/general/preview/previewparameter/

    - Nos casos onde uma funcao composable necessita de um data class para sua construcao
    acabamos dependendo de criar uma funcao Preview para ver como vai ficar. Usando
    a anotacao + a classe com a fonte de dados, podemos usar a anotacao @Preview em nossa
    funcao composable.

    - a classe que implementa PreviewParameterProvider necessita retornar uma sequence

    - é possível limitar a quantidade de previews

    Veja o exemplo abaixo
 */


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UserInfo(@PreviewParameter(UserPreviewParameterProvider::class, 3) user: User) {

    val modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
        .navigationBarsPadding()
        .statusBarsPadding()

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = user.name,
            textAlign = TextAlign.Center,
            fontSize = 45.sp
        )
    }
}