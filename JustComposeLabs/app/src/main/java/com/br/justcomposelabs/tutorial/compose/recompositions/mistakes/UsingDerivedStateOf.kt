package com.br.justcomposelabs.tutorial.compose.recompositions.mistakes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.br.justcomposelabs.tutorial.canvas.geom.triangle.ui.theme.JustComposeLabsTheme
import timber.log.Timber

/*
    https://www.linkedin.com/feed/update/urn:li:activity:7447659533109497856/

    derivedStateOf: convert one or multiple state objects into another state
        - https://developer.android.com/develop/ui/compose/side-effects#derivedstateof
        - Em compose, recomposicoes ocorrem cada vez que um objeto de Estado observavel ou um parâmetro
        de entrada de uma função composable muda.

        - Um objeto de estado ou dados de entrada podem mudar com mais frequencia do que a UI realmente necessita
        ser atualizada, isso causa recomposições desnecessarias

        - Em casos que os inputs mudem mais do que a UI necessita ser atualizada devemos usar
        derivedStateOf(https://developer.android.com/reference/kotlin/androidx/compose/runtime/package-summary#derivedStateOf(kotlin.Function0))

 */

@Composable
fun DisplayResult(count: State<Int>) {
    /*
        https://developer.android.com/reference/kotlin/androidx/compose/runtime/package-summary#derivedStateOf(kotlin.Function0)
     */
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            "Count: ${count.value}",
            style =
            TextStyle(
                fontSize = 23.sp,
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DisplayResultPreview() {
    JustComposeLabsTheme {
        /*
            https://developer.android.com/reference/kotlin/androidx/compose/runtime/package-summary#derivedStateOf(kotlin.Function0)
         */
        val a by remember { mutableIntStateOf(1) }
        val b by remember { mutableIntStateOf(1) }

        /**
         *
         derivedStateOf
         ** @see derivedStateOf(calculation: () -> T)
         - Cria um State Objetct cujo State.value é o resultado da execução da função lambda
         calculation () -> T

         - resultado fa funcao lambda é armazenado em um cache, tal que ao chamar o resultado
         State.value repetidas vezes nao causa a execução da função lambda

         ** @see androidx.compose.runtime.DerivedSnapshotState

         - Estados derivados criados sem definir  politicas de mutação acionam atualizações a cada mudanca
         de dependência. (Construor recebe um parametro que define essa politica)

         ** @see State.value
         */

        val stateResult = remember { derivedStateOf { a + b } }

        /*
            Estudar esse link
            https://slack-chats.kotlinlang.org/t/13167056/hi-i-m-trying-to-understand-the-mutation-policy-parameter-of
         */
        val nested by remember {
            derivedStateOf(policy = structuralEqualityPolicy()) {
                stateResult.also {
                    Timber.tag("derived_state_of").d("Nest DerivedStateOf: $it")
                }
            }
        }

        DisplayResult(nested)
    }
}
