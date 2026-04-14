package com.br.justcomposelabs.tutorial.compose.recompositions.mistakes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import timber.log.Timber

/**
https://share.google/aimode/MayUFI9g8DooOIGbB

- derivedStateOf policy | SnapshotMutationPolicy
- Define como o estado derivado deve comparar novos resultados
calculadors com o valor armazenado anteriormente e decidir se deve notificar
ps observadores
 ** @see androidx.compose.runtime.structuralEqualityPolicy
 *  Compara os valores usando o metodo equals(). Método mais comum
 *  utilizado para evitar recomposicoes quando o resultado final do
 *  calculo não mudou, mesmo que suas dependencias enham mudado
 *
 ** @see androidx.compose.runtime.neverEqualPolicy
 *  Considera que o novo valor nunca é igual ao Antigo, forçando
 *  toda a mudancaça nas dependencias gerar yma atualizacao independente
 *  do resultado do calculo
 *
 *  Padrao em contexto fora da composition ou observadores de snapshot
 ** @see androidx.compose.runtime.referentialEqualityPolicy
 *  Compara se as referencias de objetos sao identicas

- Casos onde é recomendado definir uma politica manualmente
- Calculos caros.
- structuralEqualityPolicy, garante que o resultado seja armazenado em cache
e so invalida o restante da UI se o dado final realmente for diferente

- Cadeias de estados
- Ao usar um derivedStateOf dentro de outro, a politica padrao
pode mudar para neverEqualPolicy, o que pode causar muitas recomposicoes
desnecessarias se nao for gerenciada com cuidado

- Evitar invalidacoes Redundantes:
- Sem uma politica clara de igualdade, o Compose
pode considerar que o estado mudou toda vez que a funcao de calculo roda
anulando o beneficio de desempenho do derivedStateOf

 */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FilterListStructuralEqualityPolicy() {
    /*
        Cenário: Filtrar lista Strings conforme o conteúdo digitado num TextField
         - Se escolher a politica de igualdade errada recomposicoes desnecessarias
         podem ocorrer

         - structuralEqualityPolicy = Sem uma politica de igualdade
         estrutural , o compose pode recompor sua lista toda vez que digitamos,
         mesmo que a lista final nao mude conforme a função calculation nao devolva
         uma lista diferente
     */

    var searchQuery by remember { mutableStateOf("") }
    val names = remember { listOf("Alice", "Alberto", "Bruno", "Caio") }

    val filteredNames by remember {
        derivedStateOf(policy = structuralEqualityPolicy()) {
            /*
                 Será executado toda vez que o usuario digitar
             */
            val filtered = names.filter { it.startsWith(searchQuery, ignoreCase = true) }
            Timber.tag("derived_state_filtered").d("Calculando lista filtrada: $filtered")
            filtered
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .systemBarsPadding()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Busque por 'A'") }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Timber.tag("derived_state_lazy_col").d("Inside Lazy Column")

            items(filteredNames, key = { it.hashCode() }) { name ->
                Text(
                    "Nome: $name",
                    style = TextStyle(
                        fontSize = 23.sp
                    )
                )
                Timber.tag("derived_state_list").d("Renderiza Item: $name")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FilterListNeverEqualPolicy() {
    /*
        Politica ruim de comparacao
     */
    var searchQuery by remember { mutableStateOf("") }
    val names = remember { listOf("Alice", "Alberto", "Bruno", "Caio") }

    val filteredNames by remember {
        derivedStateOf(policy = neverEqualPolicy()) {
            Timber.tag("derived_state_filtered").d("Calculando lista filtrada")
            names.filter { it.startsWith(searchQuery, ignoreCase = true) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .systemBarsPadding()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Busque por 'A'") }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            /*
                Com a politica de comparacao/igualdade errada a Lista é sempre atualizada conforme
                digitamos, mesmo que por exemplo a String procurada leve a remover todos os items da lista
                e o resultado seja sempre uma lista vazia ou seja sempre o mesmo resultado ainda causa
                recomposicao de um componente cujo estado nao mudou
             */
            Timber.tag("derived_state_lazy_col").d("Inside Lazy Column")

            items(filteredNames, key = { it.hashCode() }) { name ->
                Text(
                    "Nome: $name",
                    style = TextStyle(
                        fontSize = 23.sp
                    )
                )
                Timber.tag("derived_state_list").d("Renderiza Item: $name")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FilterList() {
    var searchQuery by remember { mutableStateOf("") }
    val allNames = remember { listOf("Alice", "Alberto", "Bruno", "Caio") }
    val filteredNames by remember {
        derivedStateOf(policy = structuralEqualityPolicy()) {
            if (searchQuery.isBlank()) {
                Timber.tag("derived_state_filtered").d("Blank Query")
                allNames
            } else {
                val filtered = allNames.filter { it.startsWith(searchQuery, ignoreCase = true) }
                Timber.tag("derived_state_filtered").d("[$filtered]")
                filtered
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .systemBarsPadding()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Busque por 'A'") }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Timber.tag("derived_state_lazy_col").d("Inside Lazy Column")

            items(filteredNames, key = { it.hashCode() }) { name ->
                Text(
                    "Nome: $name",
                    style = TextStyle(
                        fontSize = 23.sp
                    )
                )
                Timber.tag("derived_state_list").d("Renderiza Item: $name")
            }
        }
    }
}
