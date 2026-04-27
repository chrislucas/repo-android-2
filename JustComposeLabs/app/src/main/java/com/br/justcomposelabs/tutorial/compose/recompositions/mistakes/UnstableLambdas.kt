package com.br.justcomposelabs.tutorial.compose.recompositions.mistakes

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

/*
    fonte https://www.linkedin.com/feed/update/urn:li:activity:7447659533109497856/

   Data Class com Campo Instavel

   data class UiState(
        // instavel
       val values: List<String>
   )

   @Immutable
   data class UiState(val values: ImmutableList<String>)

   No Compose, List não é tratada como estável por padrão por dois motivos principais:
    - List em Kotlin é read-only, não necessariamente imutável de verdade.
    - A implementação concreta pode ser mutável por trás (ex.: mutableListOf() exposta como List).

    Como Resolver
        - Usar coleções realmente imutáveis, como PersistentList (kotlinx.collections.immutable).
        - Em casos controlados, usar @Immutable/@Stable com cuidado
        (só quando você realmente garante o contrato)
 */

data class User(
    val name: String,
    val age: Int,
)

@Composable
fun UnstableLambdas(users: List<User>) {
    val ctx = LocalContext.current
    LazyColumn {
        items(users) { user ->
            val onClick: () -> Unit = {
                Toast
                    .makeText(
                        ctx,
                        "Clicked on $user",
                        Toast.LENGTH_LONG,
                    ).show()
            }
            Button(onClick = onClick) {
                Text(text = user.name)
            }
        }
    }
}

private val sampleUsers =
    listOf(
        User("Alice", 25),
        User("Bob", 30),
        User("Charlie", 35),
    )

@Preview(showBackground = true)
@Composable
fun UnstableLambdasPreview() {
    JustComposeLabsTheme {
        UnstableLambdas(users = sampleUsers)
    }
}

@Preview(showBackground = true)
@Composable
fun StableLambdasPreview() {
    JustComposeLabsTheme {
        StableLambdas(users = sampleUsers)
    }
}

@Composable
fun StableLambdas(users: List<User>) {
    val ctx = LocalContext.current
    LazyColumn {
        /*
            - encapsular a criação da lambda dentro de um remember
            - usar a função key do LazyColumn para garantir
            que a lambda seja recriada apenas quando necessário
                - Sem key Compose nao sabe qual item mudou. QUalquer alteração
                na lista gera uma recomposição de todos os items.
         */
        items(users, key = { it.name }) { user ->

            val onClick: () -> Unit =
                remember(user) {
                    {
                        Toast
                            .makeText(
                                ctx,
                                "Clicked on $user",
                                Toast.LENGTH_LONG,
                            ).show()
                    }
                }

            Button(onClick = onClick) {
                Text(text = user.name)
            }
        }
    }
}
