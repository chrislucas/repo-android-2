package com.br.justcomposelabs.tutorial.google.compose.preview.largedataset

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.sp


/*
    @Preview and large data sets
    https://developer.android.com/develop/ui/compose/tooling/previews#preview-data

    Podemos criar versoes de Preview através de um conjunto de dados. Para isso precisamos
    da annotation PreviewParameter e uma classe que implemente a interface PreviewParameterProvider

    - Esse recurso permite criar varias versoes de preview sem precisar ficar repetindo métodos
    que levam a anotacao @Preview

    - Nos casos onde uma funcao composable necessita de um data class para sua construcao
    acabamos dependendo de criar uma funcao Preview para ver como vai ficar. Usando
    a anotacao + a classe com a fonte de dados, podemos usar a anotacao @Preview em nossa
    funcao composable.

    - a classe que implementa PreviewParameterProvider necessita retornar uma sequence

    - é possível limitar a quantidade de previews

    PreviewParameterProvider
    https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/preview/PreviewParameterProvider?authuser=1

    Veja o exemplo abaixo
 */



data class User(val name: String)

class UserPreviewParameterProvider : PreviewParameterProvider<User> {
    override val values = sequenceOf(
        User("Elise"),
        User("Frank"),
        User("Julia"),
        User("Mark"),
        User("Sam"),
        User("Sophie")
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserProfilePreview(
    @PreviewParameter(UserPreviewParameterProvider::class, 2) user: User
) {
    val modifier = Modifier
        .fillMaxWidth()
        .systemBarsPadding()
        .navigationBarsPadding()
        .statusBarsPadding()

    UserProfile(modifier, user)
}

@Composable
fun UserProfile(modifier: Modifier = Modifier, user: User) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = user.name,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}